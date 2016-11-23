package com.lvg.resttest.test.listeners;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import com.lvg.resttest.annotations.DataSet;

public class ServiceTestExecutionListener implements TestExecutionListener{
	
	private IDatabaseTester databaseTester;
	

	public void beforeTestClass(TestContext testContext) throws Exception {
		// DO NOTHING
		
	}

	public void prepareTestInstance(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void beforeTestMethod(TestContext testContext) throws Exception {
		DataSet dataSetAnnotation = testContext.getTestMethod().getAnnotation(DataSet.class);
		if (dataSetAnnotation == null)
			return;
		String dataSetName = dataSetAnnotation.setUpDataSet();
		if(!dataSetName.equals("")){
			databaseTester = (IDatabaseTester)testContext.getApplicationContext().getBean("databaseTester");
			XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader)testContext
					.getApplicationContext().getBean("xlsDataFileLoader");
			IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
			databaseTester.setDataSet(dataSet);
			databaseTester.onSetup();
		}
		
	}

	public void afterTestMethod(TestContext testContext) throws Exception {
		if (databaseTester != null){
			databaseTester.onTearDown();
		}
		
	}

	public void afterTestClass(TestContext testContext) throws Exception {
		// DO NOTHING		
	}
	
	

}
