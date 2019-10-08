//package com.bilin.test;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import javax.validation.Validator;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import com.amazonaws.services.iot.model.ErrorInfo;
//import com.fasterxml.jackson.core.type.TypeReference;
//
//import freemarker.template.utility.StringUtil;
//
//public class PlatformValidator implements Validator {
//    private static final String KICK_OFF = "kick";
//    private static final String NOT_LOG_IN_ERROR = "405";
//    private static final String ACCOUNT_KICKED_OFF_ERROR = "406";
//    private static final String UNAUTHORIZED_ERROR = "407";
//    private StringRedisTemplate sessionRedisTemplate;
//    private I18nProvider i18nProvider;
//    private TokenTransferMode transferMode;
//    private int expireTimeInSeconds;
//    private boolean enableUrlAuthCheck;
//    private String appId;
//    private Set<String> nonUserOperationUrls;
//
//    public PlatformValidator() {
//        this.transferMode = TokenTransferMode.misc;
//        this.expireTimeInSeconds = 86400;
//        this.enableUrlAuthCheck = false;
//    }
//
//    public void validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String token = this.transferMode.getToken(request);
//        String redisTokenKey = "sso:token:" + token;
//        String tokenError;
//        if (!StringUtils.isBlank(token) && this.sessionRedisTemplate.hasKey(redisTokenKey)) {
//            tokenError = (String)this.sessionRedisTemplate.opsForValue().get(redisTokenKey);
//            Map<String, Object> userData = (Map)SerializeUtils.fromJson(tokenError, new TypeReference<Map<String, Object>>() {
//            });
//            String tokenStatus;
//            if (userData == null) {
//                tokenStatus = this.i18nProvider.getI18nValueWithDefaultValue("global", "405", (Object[])null, "token is null or not exists");
//                ErrorInfo errorInfo = new ErrorInfo("405", tokenStatus);
//                errorInfo.setInternationalized(true);
//                throw new BusinessException(new ErrorInfo[]{errorInfo});
//            } else {
//                tokenStatus = ObjectUtils.isEmpty(userData.get("tokenStatus")) ? "" : userData.get("tokenStatus").toString();
//                String userId;
//                if ("kick".equals(tokenStatus)) {
//                    this.sessionRedisTemplate.delete(redisTokenKey);
//                    userId = this.i18nProvider.getI18nValueWithDefaultValue("global", "406", (Object[])null, "current account has been logged in elsewhere");
//                    ErrorInfo errorInfo = new ErrorInfo("406", userId);
//                    errorInfo.setInternationalized(true);
//                    throw new BusinessException(new ErrorInfo[]{errorInfo});
//                } else {
//                    if (this.expireTimeInSeconds > 0 && this.isUserOperation(request.getRequestURI())) {
//                        this.sessionRedisTemplate.expire(redisTokenKey, (long)this.expireTimeInSeconds, TimeUnit.SECONDS);
//                    }
//
//                    SystemContext.put("TM-Header-Token", token);
//                    userId = (String)userData.get("userId");
//                    if (userId != null) {
//                        SystemContext.setUserId(userId);
//                    }
//
//                    String accountId = (String)userData.get("accountId");
//                    if (accountId != null) {
//                        SystemContext.setAccountId(accountId);
//                    }
//
//                    String accountName = (String)userData.get("accountName");
//                    if (accountName != null) {
//                        SystemContext.setAccountName(accountName);
//                    }
//
//                    String userName = (String)userData.get("userName");
//                    if (userName != null) {
//                        SystemContext.setUserName(userName);
//                    }
//
//                    String tenantId = (String)userData.get("tenantId");
//                    if (tenantId != null) {
//                        SystemContext.setTenantId(tenantId);
//                    }
//
//                    String platform = (String)userData.get("platform");
//                    if (platform != null) {
//                        SystemContext.put("TM-Header-Platform", platform);
//                    }
//
//                    String device = (String)userData.get("device");
//                    if (device != null) {
//                        SystemContext.put("TM-Header-Device", device);
//                    }
//
//                    String locale = (String)userData.get("languageType");
//                    if (StringUtils.isNotBlank(locale)) {
//                        SystemContext.setLocale(locale);
//                    } else if (StringUtils.isNotBlank(CookieUtils.getCookieValue(request, "locale"))) {
//                        SystemContext.setLocale(CookieUtils.getCookieValue(request, "locale"));
//                    }
//
//                    String timeZone = (String)userData.get("timeZone");
//                    if (StringUtils.isNotBlank(timeZone)) {
//                        SystemContext.setTimeZone(timeZone);
//                    }
//
//                    String environmentToken = request.getHeader("TM-Header-Environment-Token");
//                    String environmentRedisKey;
//                    String urlAuthRedisKey;
//                    String urlAuth;
//                    if (StringUtils.isNotBlank(environmentToken)) {
//                        environmentRedisKey = "sso:environment_token:" + environmentToken;
//                        urlAuthRedisKey = (String)this.sessionRedisTemplate.opsForHash().get(environmentRedisKey, "tenantId");
//                        if (StringUtils.isNotBlank(urlAuthRedisKey) && !"null".equalsIgnoreCase(urlAuthRedisKey)) {
//                            SystemContext.setTenantId(urlAuthRedisKey);
//                        }
//
//                        urlAuth = (String)this.sessionRedisTemplate.opsForHash().get(environmentRedisKey, "projectId");
//                        if (StringUtils.isNotBlank(urlAuth) && !"null".equalsIgnoreCase(urlAuth)) {
//                            SystemContext.setProjectId(urlAuth);
//                        }
//
//                        String environmentAppId = (String)this.sessionRedisTemplate.opsForHash().get(environmentRedisKey, "appId");
//                        if (StringUtils.isNotBlank(environmentAppId) && !"null".equalsIgnoreCase(environmentAppId)) {
//                            SystemContext.setAppId(environmentAppId);
//                        }
//
//                        String enviromentUserId = (String)this.sessionRedisTemplate.opsForHash().get(environmentRedisKey, "userId");
//                        if (StringUtils.isNotBlank(enviromentUserId) && !"null".equalsIgnoreCase(enviromentUserId)) {
//                            SystemContext.setUserId(enviromentUserId);
//                        }
//
//                        if (this.expireTimeInSeconds > 0 && this.isUserOperation(request.getRequestURI())) {
//                            this.sessionRedisTemplate.expire(environmentRedisKey, (long)this.expireTimeInSeconds, TimeUnit.SECONDS);
//                        }
//                    }
//
//                    if (this.enableUrlAuthCheck) {
//                        environmentRedisKey = RequestUtils.getRequestURI(request);
//                        urlAuthRedisKey = "csp:tenants:" + SystemContext.getTenantId() + ":softs:" + this.appId + ":users:" + SystemContext.getUserId() + ":blackUrls";
//                        urlAuth = (String)this.sessionRedisTemplate.opsForValue().get(urlAuthRedisKey);
//                        if (StringUtils.isNotBlank(urlAuth)) {
//                            Map<String, List<String>> urlAuthMap = (Map)SerializeUtils.fromJson(urlAuth, new TypeReference<Map<String, List<String>>>() {
//                            });
//                            List<String> blackList = urlAuthMap != null ? (List)urlAuthMap.get(SystemContext.getUserId()) : null;
//                            if (CollectionUtils.isNotEmpty(blackList) && StringUtil.contains(blackList, environmentRedisKey)) {
//                                String unAuthorizedError = this.i18nProvider.getI18nValueWithDefaultValue("global", "407", (Object[])null, "token is null or not exists");
//                                ErrorInfo errorInfo = new ErrorInfo("407", unAuthorizedError);
//                                errorInfo.setInternationalized(true);
//                                throw new BusinessException(new ErrorInfo[]{errorInfo});
//                            }
//                        }
//                    }
//
//                }
//            }
//        } else {
//            tokenError = this.i18nProvider.getI18nValueWithDefaultValue("global", "405", (Object[])null, "token is null or not exists");
//            ErrorInfo errorInfo = new ErrorInfo("405", tokenError);
//            errorInfo.setInternationalized(true);
//            throw new BusinessException(new ErrorInfo[]{errorInfo});
//        }
//    }
//
//    public void clean() {
//        SystemContext.clean();
//    }
//
//    private boolean isUserOperation(String url) {
//        if (url.contains("?")) {
//            url = url.substring(0, url.indexOf("?"));
//        }
//
//        url = url.toLowerCase();
//        if (CollectionUtils.isNotEmpty(this.nonUserOperationUrls)) {
//            Iterator var2 = this.nonUserOperationUrls.iterator();
//
//            while(var2.hasNext()) {
//                String notOperationUrl = (String)var2.next();
//                if (StringUtil.simpleWildcardMatch(notOperationUrl.toLowerCase(), url)) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    public void setSessionRedisTemplate(StringRedisTemplate sessionRedisTemplate) {
//        this.sessionRedisTemplate = sessionRedisTemplate;
//    }
//
//    public void setI18nProvider(I18nProvider i18nProvider) {
//        this.i18nProvider = i18nProvider;
//    }
//
//    public void setTransferMode(TokenTransferMode transferMode) {
//        this.transferMode = transferMode;
//    }
//
//    public void setExpireTimeInSeconds(int expireTimeInSeconds) {
//        this.expireTimeInSeconds = expireTimeInSeconds;
//    }
//
//    public void setEnableUrlAuthCheck(boolean enableUrlAuthCheck) {
//        this.enableUrlAuthCheck = enableUrlAuthCheck;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    public void setNonUserOperationUrls(Set<String> nonUserOperationUrls) {
//        this.nonUserOperationUrls = nonUserOperationUrls;
//    }
//}
