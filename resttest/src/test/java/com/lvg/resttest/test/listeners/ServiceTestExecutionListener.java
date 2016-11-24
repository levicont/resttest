package com.lvg.resttest.test.listeners;

import org.apache.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import com.lvg.resttest.annotations.DataSet;

public class ServiceTestExecutionListener implements TestExecutionListener{
	private static final Logger LOG = Logger.getLogger(ServiceTestExecutionListener.class);
	
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
		LOG.debug("DataSet annotation is presented and not null!");
		String dataSetName = dataSetAnnotation.setUpDataSet();
		
		if(!dataSetName.equals("")){
			LOG.debug("DataSetName is: "+dataSetName);
			databaseTester = (IDatabaseTester)testContext.getApplicationContext().getBean("databaseTester");
			LOG.debug("DatabaseTester recived from context is: "+databaseTester);
			XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader)testContext
					.getApplicationContext().getBean("xlsDataFileLoader");
			LOG.debug("XlsDataFileLoader recived from context is: "+xlsDataFileLoader);
			IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
			LOG.debug("IDataSet recived from xls "+dataSetName+" is: "+dataSet.toString());
			databaseTester.setDataSet(dataSet);
			
			ITable itable = dataSet.getTable("Contact");
			if(itable !=null){
				int rowCount = itable.getRowCount();
				ITableMetaData metaData = itable.getTableMetaData();

				LOG.debug("AFTER databaseTester.setDataSet(dataSet)"+"\n Received table Contact with rowCount="+rowCount);
				for(Column c : metaData.getColumns()){
					System.out.print(c.getColumnName()+"\t\t");
					
				}
				System.out.println();
				for(int i = 0; i< rowCount; i++){
					for(Column c : metaData.getColumns()){
						System.out.print(itable.getValue(i, c.getColumnName()));
					}
					System.out.println();
				}
			}
			
			databaseTester.onSetup();
			LOG.debug("AFTER databaseTester.onSetup()");
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
