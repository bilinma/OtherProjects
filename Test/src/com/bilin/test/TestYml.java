package com.bilin.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.bilin.bo.Person;

public class TestYml {

	public static void main(String[] args) throws IOException {
        /*Student stu=new Student();
        stu.setName("路飞");
        stu.setAge(24);
        stu.setId(1);
        Tel t=new Tel();
        t.setName("张三");
        t.setTel("10123041445");
        Tel t1=new Tel();
        t1.setName("李四");
        t1.setTel("19923041455");
        List<Tel> tels=new ArrayList<Tel>();
        tels.add(t);
        tels.add(t1);
        stu.setTels(tels);
        Yaml yaml = new Yaml();
        yaml.dump(stu, new FileWriter("student.yaml"));*/
		
		
		/* Initialize data. */
        Person father = new Person();
        father.setName("simon.zhang");
        father.setAge(23);
        father.setSex("man");
        List<Person> children=new ArrayList<Person>();
        for (int i = 8; i < 10; i++) {
            Person child = new Person();
            if (i % 2 == 0) {
                child.setSex("man");
                child.setName("mary" + (i - 7));
            } else {
                child.setSex("fatel");
                child.setName("simon" + (i - 7));
            }
            child.setAge(i);
            children.add(child);
        }
        father.setChildren(children);
        Writer dumpFile = new FileWriter("testYaml.yml");

        Yaml yaml = new Yaml();
        yaml.dump(father, dumpFile);
		//read();
	}

	
    public static void  read() throws FileNotFoundException {
        File dumpFile=new File("testYaml.yml");
        Yaml yaml = new Yaml();
        FileInputStream fis =  new FileInputStream(dumpFile);
        Person father = (Person) yaml.loadAs(fis, Person.class);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(father.getName())
                .append("\t")
                .append(father.getSex())
                .append("\t")
                .append(father.getAge())
                .append("\t")
                .append(father.getChildren().size());
        System.out.println(stringBuilder.toString());

    }
	
}
