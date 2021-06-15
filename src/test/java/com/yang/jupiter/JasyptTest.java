package com.yang.jupiter;

import static org.junit.jupiter.api.Assertions.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptTest {

	@Test
	void test() {
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword("jupiter");

		String redisurl = pbeEnc.encrypt("scm-dev-db-backend-redis-master.scm-dev-db");
		System.out.println("redisurl = " + redisurl);

		String redispassword = pbeEnc.encrypt("wCHpAIJDm4");
		System.out.println("redispassword = " + redispassword);

		System.out.println("redisurl des = " + pbeEnc.decrypt(redisurl));
		System.out.println("redispassword des = " + pbeEnc.decrypt(redispassword));

		System.out.println("---- Redis ------------------------------------------");

		String dburl = pbeEnc.encrypt("jdbc:mysql://scm-dev-an2-rds-service-db-instance-1.c8hvurv3agk9.ap-northeast-2.rds.amazonaws.com:3306/scm_dev_an2_rds_service_db?useSSL=false");
		System.out.println("dburl = " + dburl);

		String dbuser = pbeEnc.encrypt("scm");
		System.out.println("dbuser = " + dbuser);
		
		String dbpassword = pbeEnc.encrypt("'!April21780'");
		System.out.println("dbpassword = " + dbpassword);

		System.out.println("dburl des = " + pbeEnc.decrypt(dburl));
		System.out.println("dbuser des = " + pbeEnc.decrypt(dbuser));
		System.out.println("dbpassword des = " + pbeEnc.decrypt(dbpassword));
		System.out.println("---- DB ------------------------------------------");
	}

}
