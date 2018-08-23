rs.slaveOk();
db.WbWeChatUser.find().forEach(function(i){
	
	var mobilePhone = i.mobilePhone;
	var weChatName = i.weChatName;
	var weChatOpenId= i.weChatOpenId;
	var balance = i.balance;
	var deposite = i.deposite;
	if(balance >0){
		try{
			var result = db.WbBalanceOrder.find({"weChatOpenId":weChatOpenId, "changeType" : "TOPUP","status" : "FINISHED","refundStatus" : { "$in" : [ "NONE","PROCESSING","PENDING"]}}).sort({_id:-1}).limit(1);
			while(result.hasNext()){
				var next = result.next();
				var payDetail = next.payDetail;
				var orderSN = next.orderSN;
				var transactionId = payDetail.transactionId;
				print(mobilePhone+","+transactionId+","+balance+",TOPUP");
			}
		}catch(e){
			print(e);
		}
	}
	
	if(deposite>0){
		try{
			var result = db.WbBalanceOrder.find({"weChatOpenId":weChatOpenId, "changeType" : "DEPOSIT","status" : "FINISHED","refundStatus" : { "$in" : [ "NONE","PROCESSING","PENDING"]}}).sort({_id:-1}).limit(1);
			while(result.hasNext()){
				var next = result.next();
				var payDetail = next.payDetail;
				var orderSN = next.orderSN;
				var transactionId = payDetail.transactionId;
				print(mobilePhone+","+transactionId+","+deposite+",DEPOSIT");
			}
		}catch(e){
			print(e);
		}
		
	}
	
	
	
})