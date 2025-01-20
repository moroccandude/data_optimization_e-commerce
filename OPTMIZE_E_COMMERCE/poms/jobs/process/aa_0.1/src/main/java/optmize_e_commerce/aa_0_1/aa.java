// ============================================================================
//
// Copyright (c) 2006-2015, Talaxie SA.
//
// Le code source a été automatiquement généré par_Talaxie Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence").
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package optmize_e_commerce.aa_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: aa Purpose: <br>
 * Description: <br>
 * 
 * @author hackercrazy575@gmail.com
 * @version 8.8.8.20241227_0728-SNAPSHOT
 * @status
 */
public class aa implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (output != null) {

				this.setProperty("output", output.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String output;

		public String getOutput() {
			return this.output;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "aa";
	private final String projectName = "OPTMIZE_E_COMMERCE";
	public Integer errorCode = null;
	private String currentComponent = "";

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					aa.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(aa.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSampleRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSampleRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_OPTMIZE_E_COMMERCE_aa = new byte[0];
		static byte[] commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[0];

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Boolean ProductNameIsNullable() {
			return true;
		}

		public Boolean ProductNameIsKey() {
			return false;
		}

		public Integer ProductNameLength() {
			return 18;
		}

		public Integer ProductNamePrecision() {
			return 0;
		}

		public String ProductNameDefault() {

			return null;

		}

		public String ProductNameComment() {

			return "";

		}

		public String ProductNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductNameOriginalDbColumnName() {

			return "ProductName";

		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public Boolean ProductCategoryIsNullable() {
			return true;
		}

		public Boolean ProductCategoryIsKey() {
			return false;
		}

		public Integer ProductCategoryLength() {
			return 15;
		}

		public Integer ProductCategoryPrecision() {
			return 0;
		}

		public String ProductCategoryDefault() {

			return null;

		}

		public String ProductCategoryComment() {

			return "";

		}

		public String ProductCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductCategoryOriginalDbColumnName() {

			return "ProductCategory";

		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public Boolean ProductSubCategoryIsNullable() {
			return true;
		}

		public Boolean ProductSubCategoryIsKey() {
			return false;
		}

		public Integer ProductSubCategoryLength() {
			return 15;
		}

		public Integer ProductSubCategoryPrecision() {
			return 0;
		}

		public String ProductSubCategoryDefault() {

			return null;

		}

		public String ProductSubCategoryComment() {

			return "";

		}

		public String ProductSubCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductSubCategoryOriginalDbColumnName() {

			return "ProductSubCategory";

		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public Boolean ProductPriceIsNullable() {
			return true;
		}

		public Boolean ProductPriceIsKey() {
			return false;
		}

		public Integer ProductPriceLength() {
			return 12;
		}

		public Integer ProductPricePrecision() {
			return 0;
		}

		public String ProductPriceDefault() {

			return null;

		}

		public String ProductPriceComment() {

			return "";

		}

		public String ProductPricePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductPriceOriginalDbColumnName() {

			return "ProductPrice";

		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public Boolean CustomerNameIsNullable() {
			return true;
		}

		public Boolean CustomerNameIsKey() {
			return false;
		}

		public Integer CustomerNameLength() {
			return 24;
		}

		public Integer CustomerNamePrecision() {
			return 0;
		}

		public String CustomerNameDefault() {

			return null;

		}

		public String CustomerNameComment() {

			return "";

		}

		public String CustomerNamePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerNameOriginalDbColumnName() {

			return "CustomerName";

		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public Boolean CustomerEmailIsNullable() {
			return true;
		}

		public Boolean CustomerEmailIsKey() {
			return false;
		}

		public Integer CustomerEmailLength() {
			return 30;
		}

		public Integer CustomerEmailPrecision() {
			return 0;
		}

		public String CustomerEmailDefault() {

			return null;

		}

		public String CustomerEmailComment() {

			return "";

		}

		public String CustomerEmailPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerEmailOriginalDbColumnName() {

			return "CustomerEmail";

		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public Boolean CustomerAddressIsNullable() {
			return true;
		}

		public Boolean CustomerAddressIsKey() {
			return false;
		}

		public Integer CustomerAddressLength() {
			return 38;
		}

		public Integer CustomerAddressPrecision() {
			return 0;
		}

		public String CustomerAddressDefault() {

			return null;

		}

		public String CustomerAddressComment() {

			return "";

		}

		public String CustomerAddressPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerAddressOriginalDbColumnName() {

			return "CustomerAddress";

		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public Boolean CustomerPhoneIsNullable() {
			return true;
		}

		public Boolean CustomerPhoneIsKey() {
			return false;
		}

		public Integer CustomerPhoneLength() {
			return 20;
		}

		public Integer CustomerPhonePrecision() {
			return 0;
		}

		public String CustomerPhoneDefault() {

			return null;

		}

		public String CustomerPhoneComment() {

			return "";

		}

		public String CustomerPhonePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerPhoneOriginalDbColumnName() {

			return "CustomerPhone";

		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public Boolean CustomerSegmentIsNullable() {
			return true;
		}

		public Boolean CustomerSegmentIsKey() {
			return false;
		}

		public Integer CustomerSegmentLength() {
			return 17;
		}

		public Integer CustomerSegmentPrecision() {
			return 0;
		}

		public String CustomerSegmentDefault() {

			return null;

		}

		public String CustomerSegmentComment() {

			return "";

		}

		public String CustomerSegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerSegmentOriginalDbColumnName() {

			return "CustomerSegment";

		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public Boolean SupplierNameIsNullable() {
			return true;
		}

		public Boolean SupplierNameIsKey() {
			return false;
		}

		public Integer SupplierNameLength() {
			return 22;
		}

		public Integer SupplierNamePrecision() {
			return 0;
		}

		public String SupplierNameDefault() {

			return null;

		}

		public String SupplierNameComment() {

			return "";

		}

		public String SupplierNamePattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierNameOriginalDbColumnName() {

			return "SupplierName";

		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public Boolean SupplierLocationIsNullable() {
			return true;
		}

		public Boolean SupplierLocationIsKey() {
			return false;
		}

		public Integer SupplierLocationLength() {
			return 14;
		}

		public Integer SupplierLocationPrecision() {
			return 0;
		}

		public String SupplierLocationDefault() {

			return null;

		}

		public String SupplierLocationComment() {

			return "";

		}

		public String SupplierLocationPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierLocationOriginalDbColumnName() {

			return "SupplierLocation";

		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		public Boolean SupplierContactIsNullable() {
			return true;
		}

		public Boolean SupplierContactIsKey() {
			return false;
		}

		public Integer SupplierContactLength() {
			return 18;
		}

		public Integer SupplierContactPrecision() {
			return 0;
		}

		public String SupplierContactDefault() {

			return null;

		}

		public String SupplierContactComment() {

			return "";

		}

		public String SupplierContactPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierContactOriginalDbColumnName() {

			return "SupplierContact";

		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public Boolean ShipperNameIsNullable() {
			return true;
		}

		public Boolean ShipperNameIsKey() {
			return false;
		}

		public Integer ShipperNameLength() {
			return 23;
		}

		public Integer ShipperNamePrecision() {
			return 0;
		}

		public String ShipperNameDefault() {

			return null;

		}

		public String ShipperNameComment() {

			return "";

		}

		public String ShipperNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipperNameOriginalDbColumnName() {

			return "ShipperName";

		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		public Boolean ShippingMethodIsNullable() {
			return true;
		}

		public Boolean ShippingMethodIsKey() {
			return false;
		}

		public Integer ShippingMethodLength() {
			return 21;
		}

		public Integer ShippingMethodPrecision() {
			return 0;
		}

		public String ShippingMethodDefault() {

			return null;

		}

		public String ShippingMethodComment() {

			return "";

		}

		public String ShippingMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String ShippingMethodOriginalDbColumnName() {

			return "ShippingMethod";

		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public Boolean QuantitySoldIsNullable() {
			return true;
		}

		public Boolean QuantitySoldIsKey() {
			return false;
		}

		public Integer QuantitySoldLength() {
			return 21;
		}

		public Integer QuantitySoldPrecision() {
			return 0;
		}

		public String QuantitySoldDefault() {

			return null;

		}

		public String QuantitySoldComment() {

			return "";

		}

		public String QuantitySoldPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantitySoldOriginalDbColumnName() {

			return "QuantitySold";

		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public Boolean TotalAmountIsNullable() {
			return true;
		}

		public Boolean TotalAmountIsKey() {
			return false;
		}

		public Integer TotalAmountLength() {
			return 8;
		}

		public Integer TotalAmountPrecision() {
			return 0;
		}

		public String TotalAmountDefault() {

			return null;

		}

		public String TotalAmountComment() {

			return "";

		}

		public String TotalAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalAmountOriginalDbColumnName() {

			return "TotalAmount";

		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Boolean DiscountAmountIsNullable() {
			return true;
		}

		public Boolean DiscountAmountIsKey() {
			return false;
		}

		public Integer DiscountAmountLength() {
			return 8;
		}

		public Integer DiscountAmountPrecision() {
			return 0;
		}

		public String DiscountAmountDefault() {

			return null;

		}

		public String DiscountAmountComment() {

			return "";

		}

		public String DiscountAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String DiscountAmountOriginalDbColumnName() {

			return "DiscountAmount";

		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Boolean NetAmountIsNullable() {
			return true;
		}

		public Boolean NetAmountIsKey() {
			return false;
		}

		public Integer NetAmountLength() {
			return 18;
		}

		public Integer NetAmountPrecision() {
			return 13;
		}

		public String NetAmountDefault() {

			return null;

		}

		public String NetAmountComment() {

			return "";

		}

		public String NetAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String NetAmountOriginalDbColumnName() {

			return "NetAmount";

		}

		public Float StockReceived;

		public Float getStockReceived() {
			return this.StockReceived;
		}

		public Boolean StockReceivedIsNullable() {
			return true;
		}

		public Boolean StockReceivedIsKey() {
			return false;
		}

		public Integer StockReceivedLength() {
			return 18;
		}

		public Integer StockReceivedPrecision() {
			return 16;
		}

		public String StockReceivedDefault() {

			return null;

		}

		public String StockReceivedComment() {

			return "";

		}

		public String StockReceivedPattern() {

			return "dd-MM-yyyy";

		}

		public String StockReceivedOriginalDbColumnName() {

			return "StockReceived";

		}

		public Float StockSold;

		public Float getStockSold() {
			return this.StockSold;
		}

		public Boolean StockSoldIsNullable() {
			return true;
		}

		public Boolean StockSoldIsKey() {
			return false;
		}

		public Integer StockSoldLength() {
			return 18;
		}

		public Integer StockSoldPrecision() {
			return 13;
		}

		public String StockSoldDefault() {

			return null;

		}

		public String StockSoldComment() {

			return "";

		}

		public String StockSoldPattern() {

			return "dd-MM-yyyy";

		}

		public String StockSoldOriginalDbColumnName() {

			return "StockSold";

		}

		public Float StockOnHand;

		public Float getStockOnHand() {
			return this.StockOnHand;
		}

		public Boolean StockOnHandIsNullable() {
			return true;
		}

		public Boolean StockOnHandIsKey() {
			return false;
		}

		public Integer StockOnHandLength() {
			return 18;
		}

		public Integer StockOnHandPrecision() {
			return 13;
		}

		public String StockOnHandDefault() {

			return null;

		}

		public String StockOnHandComment() {

			return "";

		}

		public String StockOnHandPattern() {

			return "dd-MM-yyyy";

		}

		public String StockOnHandOriginalDbColumnName() {

			return "StockOnHand";

		}

		public Integer Column22;

		public Integer getColumn22() {
			return this.Column22;
		}

		public Boolean Column22IsNullable() {
			return true;
		}

		public Boolean Column22IsKey() {
			return false;
		}

		public Integer Column22Length() {
			return 3;
		}

		public Integer Column22Precision() {
			return 0;
		}

		public String Column22Default() {

			return null;

		}

		public String Column22Comment() {

			return "";

		}

		public String Column22Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column22OriginalDbColumnName() {

			return "Column22";

		}

		public String Column23;

		public String getColumn23() {
			return this.Column23;
		}

		public Boolean Column23IsNullable() {
			return true;
		}

		public Boolean Column23IsKey() {
			return false;
		}

		public Integer Column23Length() {
			return 3;
		}

		public Integer Column23Precision() {
			return 0;
		}

		public String Column23Default() {

			return null;

		}

		public String Column23Comment() {

			return "";

		}

		public String Column23Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column23OriginalDbColumnName() {

			return "Column23";

		}

		public String Column24;

		public String getColumn24() {
			return this.Column24;
		}

		public Boolean Column24IsNullable() {
			return true;
		}

		public Boolean Column24IsKey() {
			return false;
		}

		public Integer Column24Length() {
			return 3;
		}

		public Integer Column24Precision() {
			return 0;
		}

		public String Column24Default() {

			return null;

		}

		public String Column24Comment() {

			return "";

		}

		public String Column24Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column24OriginalDbColumnName() {

			return "Column24";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Date=" + String.valueOf(Date));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",StockReceived=" + String.valueOf(StockReceived));
			sb.append(",StockSold=" + String.valueOf(StockSold));
			sb.append(",StockOnHand=" + String.valueOf(StockOnHand));
			sb.append(",Column22=" + String.valueOf(Column22));
			sb.append(",Column23=" + Column23);
			sb.append(",Column24=" + Column24);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_OPTMIZE_E_COMMERCE_aa = new byte[0];
		static byte[] commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[0];

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Boolean ProductNameIsNullable() {
			return true;
		}

		public Boolean ProductNameIsKey() {
			return false;
		}

		public Integer ProductNameLength() {
			return 18;
		}

		public Integer ProductNamePrecision() {
			return 0;
		}

		public String ProductNameDefault() {

			return null;

		}

		public String ProductNameComment() {

			return "";

		}

		public String ProductNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductNameOriginalDbColumnName() {

			return "ProductName";

		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public Boolean ProductCategoryIsNullable() {
			return true;
		}

		public Boolean ProductCategoryIsKey() {
			return false;
		}

		public Integer ProductCategoryLength() {
			return 15;
		}

		public Integer ProductCategoryPrecision() {
			return 0;
		}

		public String ProductCategoryDefault() {

			return null;

		}

		public String ProductCategoryComment() {

			return "";

		}

		public String ProductCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductCategoryOriginalDbColumnName() {

			return "ProductCategory";

		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public Boolean ProductSubCategoryIsNullable() {
			return true;
		}

		public Boolean ProductSubCategoryIsKey() {
			return false;
		}

		public Integer ProductSubCategoryLength() {
			return 15;
		}

		public Integer ProductSubCategoryPrecision() {
			return 0;
		}

		public String ProductSubCategoryDefault() {

			return null;

		}

		public String ProductSubCategoryComment() {

			return "";

		}

		public String ProductSubCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductSubCategoryOriginalDbColumnName() {

			return "ProductSubCategory";

		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public Boolean ProductPriceIsNullable() {
			return true;
		}

		public Boolean ProductPriceIsKey() {
			return false;
		}

		public Integer ProductPriceLength() {
			return 12;
		}

		public Integer ProductPricePrecision() {
			return 0;
		}

		public String ProductPriceDefault() {

			return null;

		}

		public String ProductPriceComment() {

			return "";

		}

		public String ProductPricePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductPriceOriginalDbColumnName() {

			return "ProductPrice";

		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public Boolean CustomerNameIsNullable() {
			return true;
		}

		public Boolean CustomerNameIsKey() {
			return false;
		}

		public Integer CustomerNameLength() {
			return 24;
		}

		public Integer CustomerNamePrecision() {
			return 0;
		}

		public String CustomerNameDefault() {

			return null;

		}

		public String CustomerNameComment() {

			return "";

		}

		public String CustomerNamePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerNameOriginalDbColumnName() {

			return "CustomerName";

		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public Boolean CustomerEmailIsNullable() {
			return true;
		}

		public Boolean CustomerEmailIsKey() {
			return false;
		}

		public Integer CustomerEmailLength() {
			return 30;
		}

		public Integer CustomerEmailPrecision() {
			return 0;
		}

		public String CustomerEmailDefault() {

			return null;

		}

		public String CustomerEmailComment() {

			return "";

		}

		public String CustomerEmailPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerEmailOriginalDbColumnName() {

			return "CustomerEmail";

		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public Boolean CustomerAddressIsNullable() {
			return true;
		}

		public Boolean CustomerAddressIsKey() {
			return false;
		}

		public Integer CustomerAddressLength() {
			return 38;
		}

		public Integer CustomerAddressPrecision() {
			return 0;
		}

		public String CustomerAddressDefault() {

			return null;

		}

		public String CustomerAddressComment() {

			return "";

		}

		public String CustomerAddressPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerAddressOriginalDbColumnName() {

			return "CustomerAddress";

		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public Boolean CustomerPhoneIsNullable() {
			return true;
		}

		public Boolean CustomerPhoneIsKey() {
			return false;
		}

		public Integer CustomerPhoneLength() {
			return 20;
		}

		public Integer CustomerPhonePrecision() {
			return 0;
		}

		public String CustomerPhoneDefault() {

			return null;

		}

		public String CustomerPhoneComment() {

			return "";

		}

		public String CustomerPhonePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerPhoneOriginalDbColumnName() {

			return "CustomerPhone";

		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public Boolean CustomerSegmentIsNullable() {
			return true;
		}

		public Boolean CustomerSegmentIsKey() {
			return false;
		}

		public Integer CustomerSegmentLength() {
			return 17;
		}

		public Integer CustomerSegmentPrecision() {
			return 0;
		}

		public String CustomerSegmentDefault() {

			return null;

		}

		public String CustomerSegmentComment() {

			return "";

		}

		public String CustomerSegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerSegmentOriginalDbColumnName() {

			return "CustomerSegment";

		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public Boolean SupplierNameIsNullable() {
			return true;
		}

		public Boolean SupplierNameIsKey() {
			return false;
		}

		public Integer SupplierNameLength() {
			return 22;
		}

		public Integer SupplierNamePrecision() {
			return 0;
		}

		public String SupplierNameDefault() {

			return null;

		}

		public String SupplierNameComment() {

			return "";

		}

		public String SupplierNamePattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierNameOriginalDbColumnName() {

			return "SupplierName";

		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public Boolean SupplierLocationIsNullable() {
			return true;
		}

		public Boolean SupplierLocationIsKey() {
			return false;
		}

		public Integer SupplierLocationLength() {
			return 14;
		}

		public Integer SupplierLocationPrecision() {
			return 0;
		}

		public String SupplierLocationDefault() {

			return null;

		}

		public String SupplierLocationComment() {

			return "";

		}

		public String SupplierLocationPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierLocationOriginalDbColumnName() {

			return "SupplierLocation";

		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		public Boolean SupplierContactIsNullable() {
			return true;
		}

		public Boolean SupplierContactIsKey() {
			return false;
		}

		public Integer SupplierContactLength() {
			return 18;
		}

		public Integer SupplierContactPrecision() {
			return 0;
		}

		public String SupplierContactDefault() {

			return null;

		}

		public String SupplierContactComment() {

			return "";

		}

		public String SupplierContactPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierContactOriginalDbColumnName() {

			return "SupplierContact";

		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public Boolean ShipperNameIsNullable() {
			return true;
		}

		public Boolean ShipperNameIsKey() {
			return false;
		}

		public Integer ShipperNameLength() {
			return 23;
		}

		public Integer ShipperNamePrecision() {
			return 0;
		}

		public String ShipperNameDefault() {

			return null;

		}

		public String ShipperNameComment() {

			return "";

		}

		public String ShipperNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipperNameOriginalDbColumnName() {

			return "ShipperName";

		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		public Boolean ShippingMethodIsNullable() {
			return true;
		}

		public Boolean ShippingMethodIsKey() {
			return false;
		}

		public Integer ShippingMethodLength() {
			return 21;
		}

		public Integer ShippingMethodPrecision() {
			return 0;
		}

		public String ShippingMethodDefault() {

			return null;

		}

		public String ShippingMethodComment() {

			return "";

		}

		public String ShippingMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String ShippingMethodOriginalDbColumnName() {

			return "ShippingMethod";

		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public Boolean QuantitySoldIsNullable() {
			return true;
		}

		public Boolean QuantitySoldIsKey() {
			return false;
		}

		public Integer QuantitySoldLength() {
			return 21;
		}

		public Integer QuantitySoldPrecision() {
			return 0;
		}

		public String QuantitySoldDefault() {

			return null;

		}

		public String QuantitySoldComment() {

			return "";

		}

		public String QuantitySoldPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantitySoldOriginalDbColumnName() {

			return "QuantitySold";

		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public Boolean TotalAmountIsNullable() {
			return true;
		}

		public Boolean TotalAmountIsKey() {
			return false;
		}

		public Integer TotalAmountLength() {
			return 8;
		}

		public Integer TotalAmountPrecision() {
			return 0;
		}

		public String TotalAmountDefault() {

			return null;

		}

		public String TotalAmountComment() {

			return "";

		}

		public String TotalAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalAmountOriginalDbColumnName() {

			return "TotalAmount";

		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Boolean DiscountAmountIsNullable() {
			return true;
		}

		public Boolean DiscountAmountIsKey() {
			return false;
		}

		public Integer DiscountAmountLength() {
			return 8;
		}

		public Integer DiscountAmountPrecision() {
			return 0;
		}

		public String DiscountAmountDefault() {

			return null;

		}

		public String DiscountAmountComment() {

			return "";

		}

		public String DiscountAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String DiscountAmountOriginalDbColumnName() {

			return "DiscountAmount";

		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Boolean NetAmountIsNullable() {
			return true;
		}

		public Boolean NetAmountIsKey() {
			return false;
		}

		public Integer NetAmountLength() {
			return 18;
		}

		public Integer NetAmountPrecision() {
			return 13;
		}

		public String NetAmountDefault() {

			return null;

		}

		public String NetAmountComment() {

			return "";

		}

		public String NetAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String NetAmountOriginalDbColumnName() {

			return "NetAmount";

		}

		public Float StockReceived;

		public Float getStockReceived() {
			return this.StockReceived;
		}

		public Boolean StockReceivedIsNullable() {
			return true;
		}

		public Boolean StockReceivedIsKey() {
			return false;
		}

		public Integer StockReceivedLength() {
			return 18;
		}

		public Integer StockReceivedPrecision() {
			return 16;
		}

		public String StockReceivedDefault() {

			return null;

		}

		public String StockReceivedComment() {

			return "";

		}

		public String StockReceivedPattern() {

			return "dd-MM-yyyy";

		}

		public String StockReceivedOriginalDbColumnName() {

			return "StockReceived";

		}

		public Float StockSold;

		public Float getStockSold() {
			return this.StockSold;
		}

		public Boolean StockSoldIsNullable() {
			return true;
		}

		public Boolean StockSoldIsKey() {
			return false;
		}

		public Integer StockSoldLength() {
			return 18;
		}

		public Integer StockSoldPrecision() {
			return 13;
		}

		public String StockSoldDefault() {

			return null;

		}

		public String StockSoldComment() {

			return "";

		}

		public String StockSoldPattern() {

			return "dd-MM-yyyy";

		}

		public String StockSoldOriginalDbColumnName() {

			return "StockSold";

		}

		public Float StockOnHand;

		public Float getStockOnHand() {
			return this.StockOnHand;
		}

		public Boolean StockOnHandIsNullable() {
			return true;
		}

		public Boolean StockOnHandIsKey() {
			return false;
		}

		public Integer StockOnHandLength() {
			return 18;
		}

		public Integer StockOnHandPrecision() {
			return 13;
		}

		public String StockOnHandDefault() {

			return null;

		}

		public String StockOnHandComment() {

			return "";

		}

		public String StockOnHandPattern() {

			return "dd-MM-yyyy";

		}

		public String StockOnHandOriginalDbColumnName() {

			return "StockOnHand";

		}

		public Integer Column22;

		public Integer getColumn22() {
			return this.Column22;
		}

		public Boolean Column22IsNullable() {
			return true;
		}

		public Boolean Column22IsKey() {
			return false;
		}

		public Integer Column22Length() {
			return 3;
		}

		public Integer Column22Precision() {
			return 0;
		}

		public String Column22Default() {

			return null;

		}

		public String Column22Comment() {

			return "";

		}

		public String Column22Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column22OriginalDbColumnName() {

			return "Column22";

		}

		public String Column23;

		public String getColumn23() {
			return this.Column23;
		}

		public Boolean Column23IsNullable() {
			return true;
		}

		public Boolean Column23IsKey() {
			return false;
		}

		public Integer Column23Length() {
			return 3;
		}

		public Integer Column23Precision() {
			return 0;
		}

		public String Column23Default() {

			return null;

		}

		public String Column23Comment() {

			return "";

		}

		public String Column23Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column23OriginalDbColumnName() {

			return "Column23";

		}

		public String Column24;

		public String getColumn24() {
			return this.Column24;
		}

		public Boolean Column24IsNullable() {
			return true;
		}

		public Boolean Column24IsKey() {
			return false;
		}

		public Integer Column24Length() {
			return 3;
		}

		public Integer Column24Precision() {
			return 0;
		}

		public String Column24Default() {

			return null;

		}

		public String Column24Comment() {

			return "";

		}

		public String Column24Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column24OriginalDbColumnName() {

			return "Column24";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Date=" + String.valueOf(Date));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",StockReceived=" + String.valueOf(StockReceived));
			sb.append(",StockSold=" + String.valueOf(StockSold));
			sb.append(",StockOnHand=" + String.valueOf(StockOnHand));
			sb.append(",Column22=" + String.valueOf(Column22));
			sb.append(",Column23=" + Column23);
			sb.append(",Column24=" + Column24);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_OPTMIZE_E_COMMERCE_aa = new byte[0];
		static byte[] commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[0];

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Boolean ProductNameIsNullable() {
			return true;
		}

		public Boolean ProductNameIsKey() {
			return false;
		}

		public Integer ProductNameLength() {
			return 18;
		}

		public Integer ProductNamePrecision() {
			return 0;
		}

		public String ProductNameDefault() {

			return null;

		}

		public String ProductNameComment() {

			return "";

		}

		public String ProductNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductNameOriginalDbColumnName() {

			return "ProductName";

		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public Boolean ProductCategoryIsNullable() {
			return true;
		}

		public Boolean ProductCategoryIsKey() {
			return false;
		}

		public Integer ProductCategoryLength() {
			return 15;
		}

		public Integer ProductCategoryPrecision() {
			return 0;
		}

		public String ProductCategoryDefault() {

			return null;

		}

		public String ProductCategoryComment() {

			return "";

		}

		public String ProductCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductCategoryOriginalDbColumnName() {

			return "ProductCategory";

		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public Boolean ProductSubCategoryIsNullable() {
			return true;
		}

		public Boolean ProductSubCategoryIsKey() {
			return false;
		}

		public Integer ProductSubCategoryLength() {
			return 15;
		}

		public Integer ProductSubCategoryPrecision() {
			return 0;
		}

		public String ProductSubCategoryDefault() {

			return null;

		}

		public String ProductSubCategoryComment() {

			return "";

		}

		public String ProductSubCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductSubCategoryOriginalDbColumnName() {

			return "ProductSubCategory";

		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public Boolean ProductPriceIsNullable() {
			return true;
		}

		public Boolean ProductPriceIsKey() {
			return false;
		}

		public Integer ProductPriceLength() {
			return 12;
		}

		public Integer ProductPricePrecision() {
			return 0;
		}

		public String ProductPriceDefault() {

			return null;

		}

		public String ProductPriceComment() {

			return "";

		}

		public String ProductPricePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductPriceOriginalDbColumnName() {

			return "ProductPrice";

		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public Boolean CustomerNameIsNullable() {
			return true;
		}

		public Boolean CustomerNameIsKey() {
			return false;
		}

		public Integer CustomerNameLength() {
			return 24;
		}

		public Integer CustomerNamePrecision() {
			return 0;
		}

		public String CustomerNameDefault() {

			return null;

		}

		public String CustomerNameComment() {

			return "";

		}

		public String CustomerNamePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerNameOriginalDbColumnName() {

			return "CustomerName";

		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public Boolean CustomerEmailIsNullable() {
			return true;
		}

		public Boolean CustomerEmailIsKey() {
			return false;
		}

		public Integer CustomerEmailLength() {
			return 30;
		}

		public Integer CustomerEmailPrecision() {
			return 0;
		}

		public String CustomerEmailDefault() {

			return null;

		}

		public String CustomerEmailComment() {

			return "";

		}

		public String CustomerEmailPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerEmailOriginalDbColumnName() {

			return "CustomerEmail";

		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public Boolean CustomerAddressIsNullable() {
			return true;
		}

		public Boolean CustomerAddressIsKey() {
			return false;
		}

		public Integer CustomerAddressLength() {
			return 38;
		}

		public Integer CustomerAddressPrecision() {
			return 0;
		}

		public String CustomerAddressDefault() {

			return null;

		}

		public String CustomerAddressComment() {

			return "";

		}

		public String CustomerAddressPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerAddressOriginalDbColumnName() {

			return "CustomerAddress";

		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public Boolean CustomerPhoneIsNullable() {
			return true;
		}

		public Boolean CustomerPhoneIsKey() {
			return false;
		}

		public Integer CustomerPhoneLength() {
			return 20;
		}

		public Integer CustomerPhonePrecision() {
			return 0;
		}

		public String CustomerPhoneDefault() {

			return null;

		}

		public String CustomerPhoneComment() {

			return "";

		}

		public String CustomerPhonePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerPhoneOriginalDbColumnName() {

			return "CustomerPhone";

		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public Boolean CustomerSegmentIsNullable() {
			return true;
		}

		public Boolean CustomerSegmentIsKey() {
			return false;
		}

		public Integer CustomerSegmentLength() {
			return 17;
		}

		public Integer CustomerSegmentPrecision() {
			return 0;
		}

		public String CustomerSegmentDefault() {

			return null;

		}

		public String CustomerSegmentComment() {

			return "";

		}

		public String CustomerSegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerSegmentOriginalDbColumnName() {

			return "CustomerSegment";

		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public Boolean SupplierNameIsNullable() {
			return true;
		}

		public Boolean SupplierNameIsKey() {
			return false;
		}

		public Integer SupplierNameLength() {
			return 22;
		}

		public Integer SupplierNamePrecision() {
			return 0;
		}

		public String SupplierNameDefault() {

			return null;

		}

		public String SupplierNameComment() {

			return "";

		}

		public String SupplierNamePattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierNameOriginalDbColumnName() {

			return "SupplierName";

		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public Boolean SupplierLocationIsNullable() {
			return true;
		}

		public Boolean SupplierLocationIsKey() {
			return false;
		}

		public Integer SupplierLocationLength() {
			return 14;
		}

		public Integer SupplierLocationPrecision() {
			return 0;
		}

		public String SupplierLocationDefault() {

			return null;

		}

		public String SupplierLocationComment() {

			return "";

		}

		public String SupplierLocationPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierLocationOriginalDbColumnName() {

			return "SupplierLocation";

		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		public Boolean SupplierContactIsNullable() {
			return true;
		}

		public Boolean SupplierContactIsKey() {
			return false;
		}

		public Integer SupplierContactLength() {
			return 18;
		}

		public Integer SupplierContactPrecision() {
			return 0;
		}

		public String SupplierContactDefault() {

			return null;

		}

		public String SupplierContactComment() {

			return "";

		}

		public String SupplierContactPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierContactOriginalDbColumnName() {

			return "SupplierContact";

		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public Boolean ShipperNameIsNullable() {
			return true;
		}

		public Boolean ShipperNameIsKey() {
			return false;
		}

		public Integer ShipperNameLength() {
			return 23;
		}

		public Integer ShipperNamePrecision() {
			return 0;
		}

		public String ShipperNameDefault() {

			return null;

		}

		public String ShipperNameComment() {

			return "";

		}

		public String ShipperNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipperNameOriginalDbColumnName() {

			return "ShipperName";

		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		public Boolean ShippingMethodIsNullable() {
			return true;
		}

		public Boolean ShippingMethodIsKey() {
			return false;
		}

		public Integer ShippingMethodLength() {
			return 21;
		}

		public Integer ShippingMethodPrecision() {
			return 0;
		}

		public String ShippingMethodDefault() {

			return null;

		}

		public String ShippingMethodComment() {

			return "";

		}

		public String ShippingMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String ShippingMethodOriginalDbColumnName() {

			return "ShippingMethod";

		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public Boolean QuantitySoldIsNullable() {
			return true;
		}

		public Boolean QuantitySoldIsKey() {
			return false;
		}

		public Integer QuantitySoldLength() {
			return 21;
		}

		public Integer QuantitySoldPrecision() {
			return 0;
		}

		public String QuantitySoldDefault() {

			return null;

		}

		public String QuantitySoldComment() {

			return "";

		}

		public String QuantitySoldPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantitySoldOriginalDbColumnName() {

			return "QuantitySold";

		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public Boolean TotalAmountIsNullable() {
			return true;
		}

		public Boolean TotalAmountIsKey() {
			return false;
		}

		public Integer TotalAmountLength() {
			return 8;
		}

		public Integer TotalAmountPrecision() {
			return 0;
		}

		public String TotalAmountDefault() {

			return null;

		}

		public String TotalAmountComment() {

			return "";

		}

		public String TotalAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalAmountOriginalDbColumnName() {

			return "TotalAmount";

		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Boolean DiscountAmountIsNullable() {
			return true;
		}

		public Boolean DiscountAmountIsKey() {
			return false;
		}

		public Integer DiscountAmountLength() {
			return 8;
		}

		public Integer DiscountAmountPrecision() {
			return 0;
		}

		public String DiscountAmountDefault() {

			return null;

		}

		public String DiscountAmountComment() {

			return "";

		}

		public String DiscountAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String DiscountAmountOriginalDbColumnName() {

			return "DiscountAmount";

		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Boolean NetAmountIsNullable() {
			return true;
		}

		public Boolean NetAmountIsKey() {
			return false;
		}

		public Integer NetAmountLength() {
			return 18;
		}

		public Integer NetAmountPrecision() {
			return 13;
		}

		public String NetAmountDefault() {

			return null;

		}

		public String NetAmountComment() {

			return "";

		}

		public String NetAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String NetAmountOriginalDbColumnName() {

			return "NetAmount";

		}

		public Float StockReceived;

		public Float getStockReceived() {
			return this.StockReceived;
		}

		public Boolean StockReceivedIsNullable() {
			return true;
		}

		public Boolean StockReceivedIsKey() {
			return false;
		}

		public Integer StockReceivedLength() {
			return 18;
		}

		public Integer StockReceivedPrecision() {
			return 16;
		}

		public String StockReceivedDefault() {

			return null;

		}

		public String StockReceivedComment() {

			return "";

		}

		public String StockReceivedPattern() {

			return "dd-MM-yyyy";

		}

		public String StockReceivedOriginalDbColumnName() {

			return "StockReceived";

		}

		public Float StockSold;

		public Float getStockSold() {
			return this.StockSold;
		}

		public Boolean StockSoldIsNullable() {
			return true;
		}

		public Boolean StockSoldIsKey() {
			return false;
		}

		public Integer StockSoldLength() {
			return 18;
		}

		public Integer StockSoldPrecision() {
			return 13;
		}

		public String StockSoldDefault() {

			return null;

		}

		public String StockSoldComment() {

			return "";

		}

		public String StockSoldPattern() {

			return "dd-MM-yyyy";

		}

		public String StockSoldOriginalDbColumnName() {

			return "StockSold";

		}

		public Float StockOnHand;

		public Float getStockOnHand() {
			return this.StockOnHand;
		}

		public Boolean StockOnHandIsNullable() {
			return true;
		}

		public Boolean StockOnHandIsKey() {
			return false;
		}

		public Integer StockOnHandLength() {
			return 18;
		}

		public Integer StockOnHandPrecision() {
			return 13;
		}

		public String StockOnHandDefault() {

			return null;

		}

		public String StockOnHandComment() {

			return "";

		}

		public String StockOnHandPattern() {

			return "dd-MM-yyyy";

		}

		public String StockOnHandOriginalDbColumnName() {

			return "StockOnHand";

		}

		public Integer Column22;

		public Integer getColumn22() {
			return this.Column22;
		}

		public Boolean Column22IsNullable() {
			return true;
		}

		public Boolean Column22IsKey() {
			return false;
		}

		public Integer Column22Length() {
			return 3;
		}

		public Integer Column22Precision() {
			return 0;
		}

		public String Column22Default() {

			return null;

		}

		public String Column22Comment() {

			return "";

		}

		public String Column22Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column22OriginalDbColumnName() {

			return "Column22";

		}

		public String Column23;

		public String getColumn23() {
			return this.Column23;
		}

		public Boolean Column23IsNullable() {
			return true;
		}

		public Boolean Column23IsKey() {
			return false;
		}

		public Integer Column23Length() {
			return 3;
		}

		public Integer Column23Precision() {
			return 0;
		}

		public String Column23Default() {

			return null;

		}

		public String Column23Comment() {

			return "";

		}

		public String Column23Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column23OriginalDbColumnName() {

			return "Column23";

		}

		public String Column24;

		public String getColumn24() {
			return this.Column24;
		}

		public Boolean Column24IsNullable() {
			return true;
		}

		public Boolean Column24IsKey() {
			return false;
		}

		public Integer Column24Length() {
			return 3;
		}

		public Integer Column24Precision() {
			return 0;
		}

		public String Column24Default() {

			return null;

		}

		public String Column24Comment() {

			return "";

		}

		public String Column24Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column24OriginalDbColumnName() {

			return "Column24";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Date=" + String.valueOf(Date));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",StockReceived=" + String.valueOf(StockReceived));
			sb.append(",StockSold=" + String.valueOf(StockSold));
			sb.append(",StockOnHand=" + String.valueOf(StockOnHand));
			sb.append(",Column22=" + String.valueOf(Column22));
			sb.append(",Column23=" + Column23);
			sb.append(",Column24=" + Column24);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_OPTMIZE_E_COMMERCE_aa = new byte[0];
		static byte[] commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[0];

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Boolean ProductNameIsNullable() {
			return true;
		}

		public Boolean ProductNameIsKey() {
			return false;
		}

		public Integer ProductNameLength() {
			return 18;
		}

		public Integer ProductNamePrecision() {
			return 0;
		}

		public String ProductNameDefault() {

			return null;

		}

		public String ProductNameComment() {

			return "";

		}

		public String ProductNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductNameOriginalDbColumnName() {

			return "ProductName";

		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public Boolean ProductCategoryIsNullable() {
			return true;
		}

		public Boolean ProductCategoryIsKey() {
			return false;
		}

		public Integer ProductCategoryLength() {
			return 15;
		}

		public Integer ProductCategoryPrecision() {
			return 0;
		}

		public String ProductCategoryDefault() {

			return null;

		}

		public String ProductCategoryComment() {

			return "";

		}

		public String ProductCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductCategoryOriginalDbColumnName() {

			return "ProductCategory";

		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public Boolean ProductSubCategoryIsNullable() {
			return true;
		}

		public Boolean ProductSubCategoryIsKey() {
			return false;
		}

		public Integer ProductSubCategoryLength() {
			return 15;
		}

		public Integer ProductSubCategoryPrecision() {
			return 0;
		}

		public String ProductSubCategoryDefault() {

			return null;

		}

		public String ProductSubCategoryComment() {

			return "";

		}

		public String ProductSubCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductSubCategoryOriginalDbColumnName() {

			return "ProductSubCategory";

		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public Boolean ProductPriceIsNullable() {
			return true;
		}

		public Boolean ProductPriceIsKey() {
			return false;
		}

		public Integer ProductPriceLength() {
			return 12;
		}

		public Integer ProductPricePrecision() {
			return 0;
		}

		public String ProductPriceDefault() {

			return null;

		}

		public String ProductPriceComment() {

			return "";

		}

		public String ProductPricePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductPriceOriginalDbColumnName() {

			return "ProductPrice";

		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public Boolean CustomerNameIsNullable() {
			return true;
		}

		public Boolean CustomerNameIsKey() {
			return false;
		}

		public Integer CustomerNameLength() {
			return 24;
		}

		public Integer CustomerNamePrecision() {
			return 0;
		}

		public String CustomerNameDefault() {

			return null;

		}

		public String CustomerNameComment() {

			return "";

		}

		public String CustomerNamePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerNameOriginalDbColumnName() {

			return "CustomerName";

		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public Boolean CustomerEmailIsNullable() {
			return true;
		}

		public Boolean CustomerEmailIsKey() {
			return false;
		}

		public Integer CustomerEmailLength() {
			return 30;
		}

		public Integer CustomerEmailPrecision() {
			return 0;
		}

		public String CustomerEmailDefault() {

			return null;

		}

		public String CustomerEmailComment() {

			return "";

		}

		public String CustomerEmailPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerEmailOriginalDbColumnName() {

			return "CustomerEmail";

		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public Boolean CustomerAddressIsNullable() {
			return true;
		}

		public Boolean CustomerAddressIsKey() {
			return false;
		}

		public Integer CustomerAddressLength() {
			return 38;
		}

		public Integer CustomerAddressPrecision() {
			return 0;
		}

		public String CustomerAddressDefault() {

			return null;

		}

		public String CustomerAddressComment() {

			return "";

		}

		public String CustomerAddressPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerAddressOriginalDbColumnName() {

			return "CustomerAddress";

		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public Boolean CustomerPhoneIsNullable() {
			return true;
		}

		public Boolean CustomerPhoneIsKey() {
			return false;
		}

		public Integer CustomerPhoneLength() {
			return 20;
		}

		public Integer CustomerPhonePrecision() {
			return 0;
		}

		public String CustomerPhoneDefault() {

			return null;

		}

		public String CustomerPhoneComment() {

			return "";

		}

		public String CustomerPhonePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerPhoneOriginalDbColumnName() {

			return "CustomerPhone";

		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public Boolean CustomerSegmentIsNullable() {
			return true;
		}

		public Boolean CustomerSegmentIsKey() {
			return false;
		}

		public Integer CustomerSegmentLength() {
			return 17;
		}

		public Integer CustomerSegmentPrecision() {
			return 0;
		}

		public String CustomerSegmentDefault() {

			return null;

		}

		public String CustomerSegmentComment() {

			return "";

		}

		public String CustomerSegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerSegmentOriginalDbColumnName() {

			return "CustomerSegment";

		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public Boolean SupplierNameIsNullable() {
			return true;
		}

		public Boolean SupplierNameIsKey() {
			return false;
		}

		public Integer SupplierNameLength() {
			return 22;
		}

		public Integer SupplierNamePrecision() {
			return 0;
		}

		public String SupplierNameDefault() {

			return null;

		}

		public String SupplierNameComment() {

			return "";

		}

		public String SupplierNamePattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierNameOriginalDbColumnName() {

			return "SupplierName";

		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public Boolean SupplierLocationIsNullable() {
			return true;
		}

		public Boolean SupplierLocationIsKey() {
			return false;
		}

		public Integer SupplierLocationLength() {
			return 14;
		}

		public Integer SupplierLocationPrecision() {
			return 0;
		}

		public String SupplierLocationDefault() {

			return null;

		}

		public String SupplierLocationComment() {

			return "";

		}

		public String SupplierLocationPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierLocationOriginalDbColumnName() {

			return "SupplierLocation";

		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		public Boolean SupplierContactIsNullable() {
			return true;
		}

		public Boolean SupplierContactIsKey() {
			return false;
		}

		public Integer SupplierContactLength() {
			return 18;
		}

		public Integer SupplierContactPrecision() {
			return 0;
		}

		public String SupplierContactDefault() {

			return null;

		}

		public String SupplierContactComment() {

			return "";

		}

		public String SupplierContactPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierContactOriginalDbColumnName() {

			return "SupplierContact";

		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public Boolean ShipperNameIsNullable() {
			return true;
		}

		public Boolean ShipperNameIsKey() {
			return false;
		}

		public Integer ShipperNameLength() {
			return 23;
		}

		public Integer ShipperNamePrecision() {
			return 0;
		}

		public String ShipperNameDefault() {

			return null;

		}

		public String ShipperNameComment() {

			return "";

		}

		public String ShipperNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipperNameOriginalDbColumnName() {

			return "ShipperName";

		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		public Boolean ShippingMethodIsNullable() {
			return true;
		}

		public Boolean ShippingMethodIsKey() {
			return false;
		}

		public Integer ShippingMethodLength() {
			return 21;
		}

		public Integer ShippingMethodPrecision() {
			return 0;
		}

		public String ShippingMethodDefault() {

			return null;

		}

		public String ShippingMethodComment() {

			return "";

		}

		public String ShippingMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String ShippingMethodOriginalDbColumnName() {

			return "ShippingMethod";

		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public Boolean QuantitySoldIsNullable() {
			return true;
		}

		public Boolean QuantitySoldIsKey() {
			return false;
		}

		public Integer QuantitySoldLength() {
			return 21;
		}

		public Integer QuantitySoldPrecision() {
			return 0;
		}

		public String QuantitySoldDefault() {

			return null;

		}

		public String QuantitySoldComment() {

			return "";

		}

		public String QuantitySoldPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantitySoldOriginalDbColumnName() {

			return "QuantitySold";

		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public Boolean TotalAmountIsNullable() {
			return true;
		}

		public Boolean TotalAmountIsKey() {
			return false;
		}

		public Integer TotalAmountLength() {
			return 8;
		}

		public Integer TotalAmountPrecision() {
			return 0;
		}

		public String TotalAmountDefault() {

			return null;

		}

		public String TotalAmountComment() {

			return "";

		}

		public String TotalAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalAmountOriginalDbColumnName() {

			return "TotalAmount";

		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Boolean DiscountAmountIsNullable() {
			return true;
		}

		public Boolean DiscountAmountIsKey() {
			return false;
		}

		public Integer DiscountAmountLength() {
			return 8;
		}

		public Integer DiscountAmountPrecision() {
			return 0;
		}

		public String DiscountAmountDefault() {

			return null;

		}

		public String DiscountAmountComment() {

			return "";

		}

		public String DiscountAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String DiscountAmountOriginalDbColumnName() {

			return "DiscountAmount";

		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Boolean NetAmountIsNullable() {
			return true;
		}

		public Boolean NetAmountIsKey() {
			return false;
		}

		public Integer NetAmountLength() {
			return 18;
		}

		public Integer NetAmountPrecision() {
			return 13;
		}

		public String NetAmountDefault() {

			return null;

		}

		public String NetAmountComment() {

			return "";

		}

		public String NetAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String NetAmountOriginalDbColumnName() {

			return "NetAmount";

		}

		public Float StockReceived;

		public Float getStockReceived() {
			return this.StockReceived;
		}

		public Boolean StockReceivedIsNullable() {
			return true;
		}

		public Boolean StockReceivedIsKey() {
			return false;
		}

		public Integer StockReceivedLength() {
			return 18;
		}

		public Integer StockReceivedPrecision() {
			return 16;
		}

		public String StockReceivedDefault() {

			return null;

		}

		public String StockReceivedComment() {

			return "";

		}

		public String StockReceivedPattern() {

			return "dd-MM-yyyy";

		}

		public String StockReceivedOriginalDbColumnName() {

			return "StockReceived";

		}

		public Float StockSold;

		public Float getStockSold() {
			return this.StockSold;
		}

		public Boolean StockSoldIsNullable() {
			return true;
		}

		public Boolean StockSoldIsKey() {
			return false;
		}

		public Integer StockSoldLength() {
			return 18;
		}

		public Integer StockSoldPrecision() {
			return 13;
		}

		public String StockSoldDefault() {

			return null;

		}

		public String StockSoldComment() {

			return "";

		}

		public String StockSoldPattern() {

			return "dd-MM-yyyy";

		}

		public String StockSoldOriginalDbColumnName() {

			return "StockSold";

		}

		public Float StockOnHand;

		public Float getStockOnHand() {
			return this.StockOnHand;
		}

		public Boolean StockOnHandIsNullable() {
			return true;
		}

		public Boolean StockOnHandIsKey() {
			return false;
		}

		public Integer StockOnHandLength() {
			return 18;
		}

		public Integer StockOnHandPrecision() {
			return 13;
		}

		public String StockOnHandDefault() {

			return null;

		}

		public String StockOnHandComment() {

			return "";

		}

		public String StockOnHandPattern() {

			return "dd-MM-yyyy";

		}

		public String StockOnHandOriginalDbColumnName() {

			return "StockOnHand";

		}

		public Integer Column22;

		public Integer getColumn22() {
			return this.Column22;
		}

		public Boolean Column22IsNullable() {
			return true;
		}

		public Boolean Column22IsKey() {
			return false;
		}

		public Integer Column22Length() {
			return 3;
		}

		public Integer Column22Precision() {
			return 0;
		}

		public String Column22Default() {

			return null;

		}

		public String Column22Comment() {

			return "";

		}

		public String Column22Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column22OriginalDbColumnName() {

			return "Column22";

		}

		public String Column23;

		public String getColumn23() {
			return this.Column23;
		}

		public Boolean Column23IsNullable() {
			return true;
		}

		public Boolean Column23IsKey() {
			return false;
		}

		public Integer Column23Length() {
			return 3;
		}

		public Integer Column23Precision() {
			return 0;
		}

		public String Column23Default() {

			return null;

		}

		public String Column23Comment() {

			return "";

		}

		public String Column23Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column23OriginalDbColumnName() {

			return "Column23";

		}

		public String Column24;

		public String getColumn24() {
			return this.Column24;
		}

		public Boolean Column24IsNullable() {
			return true;
		}

		public Boolean Column24IsKey() {
			return false;
		}

		public Integer Column24Length() {
			return 3;
		}

		public Integer Column24Precision() {
			return 0;
		}

		public String Column24Default() {

			return null;

		}

		public String Column24Comment() {

			return "";

		}

		public String Column24Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column24OriginalDbColumnName() {

			return "Column24";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Date=" + String.valueOf(Date));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",StockReceived=" + String.valueOf(StockReceived));
			sb.append(",StockSold=" + String.valueOf(StockSold));
			sb.append(",StockOnHand=" + String.valueOf(StockOnHand));
			sb.append(",Column22=" + String.valueOf(Column22));
			sb.append(",Column23=" + Column23);
			sb.append(",Column24=" + Column24);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_OPTMIZE_E_COMMERCE_aa = new byte[0];
		static byte[] commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[0];

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 10;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "dd-MM-yyyy";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		public String ProductName;

		public String getProductName() {
			return this.ProductName;
		}

		public Boolean ProductNameIsNullable() {
			return true;
		}

		public Boolean ProductNameIsKey() {
			return false;
		}

		public Integer ProductNameLength() {
			return 18;
		}

		public Integer ProductNamePrecision() {
			return 0;
		}

		public String ProductNameDefault() {

			return null;

		}

		public String ProductNameComment() {

			return "";

		}

		public String ProductNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductNameOriginalDbColumnName() {

			return "ProductName";

		}

		public String ProductCategory;

		public String getProductCategory() {
			return this.ProductCategory;
		}

		public Boolean ProductCategoryIsNullable() {
			return true;
		}

		public Boolean ProductCategoryIsKey() {
			return false;
		}

		public Integer ProductCategoryLength() {
			return 15;
		}

		public Integer ProductCategoryPrecision() {
			return 0;
		}

		public String ProductCategoryDefault() {

			return null;

		}

		public String ProductCategoryComment() {

			return "";

		}

		public String ProductCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductCategoryOriginalDbColumnName() {

			return "ProductCategory";

		}

		public String ProductSubCategory;

		public String getProductSubCategory() {
			return this.ProductSubCategory;
		}

		public Boolean ProductSubCategoryIsNullable() {
			return true;
		}

		public Boolean ProductSubCategoryIsKey() {
			return false;
		}

		public Integer ProductSubCategoryLength() {
			return 15;
		}

		public Integer ProductSubCategoryPrecision() {
			return 0;
		}

		public String ProductSubCategoryDefault() {

			return null;

		}

		public String ProductSubCategoryComment() {

			return "";

		}

		public String ProductSubCategoryPattern() {

			return "dd-MM-yyyy";

		}

		public String ProductSubCategoryOriginalDbColumnName() {

			return "ProductSubCategory";

		}

		public String ProductPrice;

		public String getProductPrice() {
			return this.ProductPrice;
		}

		public Boolean ProductPriceIsNullable() {
			return true;
		}

		public Boolean ProductPriceIsKey() {
			return false;
		}

		public Integer ProductPriceLength() {
			return 12;
		}

		public Integer ProductPricePrecision() {
			return 0;
		}

		public String ProductPriceDefault() {

			return null;

		}

		public String ProductPriceComment() {

			return "";

		}

		public String ProductPricePattern() {

			return "dd-MM-yyyy";

		}

		public String ProductPriceOriginalDbColumnName() {

			return "ProductPrice";

		}

		public String CustomerName;

		public String getCustomerName() {
			return this.CustomerName;
		}

		public Boolean CustomerNameIsNullable() {
			return true;
		}

		public Boolean CustomerNameIsKey() {
			return false;
		}

		public Integer CustomerNameLength() {
			return 24;
		}

		public Integer CustomerNamePrecision() {
			return 0;
		}

		public String CustomerNameDefault() {

			return null;

		}

		public String CustomerNameComment() {

			return "";

		}

		public String CustomerNamePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerNameOriginalDbColumnName() {

			return "CustomerName";

		}

		public String CustomerEmail;

		public String getCustomerEmail() {
			return this.CustomerEmail;
		}

		public Boolean CustomerEmailIsNullable() {
			return true;
		}

		public Boolean CustomerEmailIsKey() {
			return false;
		}

		public Integer CustomerEmailLength() {
			return 30;
		}

		public Integer CustomerEmailPrecision() {
			return 0;
		}

		public String CustomerEmailDefault() {

			return null;

		}

		public String CustomerEmailComment() {

			return "";

		}

		public String CustomerEmailPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerEmailOriginalDbColumnName() {

			return "CustomerEmail";

		}

		public String CustomerAddress;

		public String getCustomerAddress() {
			return this.CustomerAddress;
		}

		public Boolean CustomerAddressIsNullable() {
			return true;
		}

		public Boolean CustomerAddressIsKey() {
			return false;
		}

		public Integer CustomerAddressLength() {
			return 38;
		}

		public Integer CustomerAddressPrecision() {
			return 0;
		}

		public String CustomerAddressDefault() {

			return null;

		}

		public String CustomerAddressComment() {

			return "";

		}

		public String CustomerAddressPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerAddressOriginalDbColumnName() {

			return "CustomerAddress";

		}

		public String CustomerPhone;

		public String getCustomerPhone() {
			return this.CustomerPhone;
		}

		public Boolean CustomerPhoneIsNullable() {
			return true;
		}

		public Boolean CustomerPhoneIsKey() {
			return false;
		}

		public Integer CustomerPhoneLength() {
			return 20;
		}

		public Integer CustomerPhonePrecision() {
			return 0;
		}

		public String CustomerPhoneDefault() {

			return null;

		}

		public String CustomerPhoneComment() {

			return "";

		}

		public String CustomerPhonePattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerPhoneOriginalDbColumnName() {

			return "CustomerPhone";

		}

		public String CustomerSegment;

		public String getCustomerSegment() {
			return this.CustomerSegment;
		}

		public Boolean CustomerSegmentIsNullable() {
			return true;
		}

		public Boolean CustomerSegmentIsKey() {
			return false;
		}

		public Integer CustomerSegmentLength() {
			return 17;
		}

		public Integer CustomerSegmentPrecision() {
			return 0;
		}

		public String CustomerSegmentDefault() {

			return null;

		}

		public String CustomerSegmentComment() {

			return "";

		}

		public String CustomerSegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerSegmentOriginalDbColumnName() {

			return "CustomerSegment";

		}

		public String SupplierName;

		public String getSupplierName() {
			return this.SupplierName;
		}

		public Boolean SupplierNameIsNullable() {
			return true;
		}

		public Boolean SupplierNameIsKey() {
			return false;
		}

		public Integer SupplierNameLength() {
			return 22;
		}

		public Integer SupplierNamePrecision() {
			return 0;
		}

		public String SupplierNameDefault() {

			return null;

		}

		public String SupplierNameComment() {

			return "";

		}

		public String SupplierNamePattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierNameOriginalDbColumnName() {

			return "SupplierName";

		}

		public String SupplierLocation;

		public String getSupplierLocation() {
			return this.SupplierLocation;
		}

		public Boolean SupplierLocationIsNullable() {
			return true;
		}

		public Boolean SupplierLocationIsKey() {
			return false;
		}

		public Integer SupplierLocationLength() {
			return 14;
		}

		public Integer SupplierLocationPrecision() {
			return 0;
		}

		public String SupplierLocationDefault() {

			return null;

		}

		public String SupplierLocationComment() {

			return "";

		}

		public String SupplierLocationPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierLocationOriginalDbColumnName() {

			return "SupplierLocation";

		}

		public String SupplierContact;

		public String getSupplierContact() {
			return this.SupplierContact;
		}

		public Boolean SupplierContactIsNullable() {
			return true;
		}

		public Boolean SupplierContactIsKey() {
			return false;
		}

		public Integer SupplierContactLength() {
			return 18;
		}

		public Integer SupplierContactPrecision() {
			return 0;
		}

		public String SupplierContactDefault() {

			return null;

		}

		public String SupplierContactComment() {

			return "";

		}

		public String SupplierContactPattern() {

			return "dd-MM-yyyy";

		}

		public String SupplierContactOriginalDbColumnName() {

			return "SupplierContact";

		}

		public String ShipperName;

		public String getShipperName() {
			return this.ShipperName;
		}

		public Boolean ShipperNameIsNullable() {
			return true;
		}

		public Boolean ShipperNameIsKey() {
			return false;
		}

		public Integer ShipperNameLength() {
			return 23;
		}

		public Integer ShipperNamePrecision() {
			return 0;
		}

		public String ShipperNameDefault() {

			return null;

		}

		public String ShipperNameComment() {

			return "";

		}

		public String ShipperNamePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipperNameOriginalDbColumnName() {

			return "ShipperName";

		}

		public String ShippingMethod;

		public String getShippingMethod() {
			return this.ShippingMethod;
		}

		public Boolean ShippingMethodIsNullable() {
			return true;
		}

		public Boolean ShippingMethodIsKey() {
			return false;
		}

		public Integer ShippingMethodLength() {
			return 21;
		}

		public Integer ShippingMethodPrecision() {
			return 0;
		}

		public String ShippingMethodDefault() {

			return null;

		}

		public String ShippingMethodComment() {

			return "";

		}

		public String ShippingMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String ShippingMethodOriginalDbColumnName() {

			return "ShippingMethod";

		}

		public String QuantitySold;

		public String getQuantitySold() {
			return this.QuantitySold;
		}

		public Boolean QuantitySoldIsNullable() {
			return true;
		}

		public Boolean QuantitySoldIsKey() {
			return false;
		}

		public Integer QuantitySoldLength() {
			return 21;
		}

		public Integer QuantitySoldPrecision() {
			return 0;
		}

		public String QuantitySoldDefault() {

			return null;

		}

		public String QuantitySoldComment() {

			return "";

		}

		public String QuantitySoldPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantitySoldOriginalDbColumnName() {

			return "QuantitySold";

		}

		public String TotalAmount;

		public String getTotalAmount() {
			return this.TotalAmount;
		}

		public Boolean TotalAmountIsNullable() {
			return true;
		}

		public Boolean TotalAmountIsKey() {
			return false;
		}

		public Integer TotalAmountLength() {
			return 8;
		}

		public Integer TotalAmountPrecision() {
			return 0;
		}

		public String TotalAmountDefault() {

			return null;

		}

		public String TotalAmountComment() {

			return "";

		}

		public String TotalAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalAmountOriginalDbColumnName() {

			return "TotalAmount";

		}

		public String DiscountAmount;

		public String getDiscountAmount() {
			return this.DiscountAmount;
		}

		public Boolean DiscountAmountIsNullable() {
			return true;
		}

		public Boolean DiscountAmountIsKey() {
			return false;
		}

		public Integer DiscountAmountLength() {
			return 8;
		}

		public Integer DiscountAmountPrecision() {
			return 0;
		}

		public String DiscountAmountDefault() {

			return null;

		}

		public String DiscountAmountComment() {

			return "";

		}

		public String DiscountAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String DiscountAmountOriginalDbColumnName() {

			return "DiscountAmount";

		}

		public Float NetAmount;

		public Float getNetAmount() {
			return this.NetAmount;
		}

		public Boolean NetAmountIsNullable() {
			return true;
		}

		public Boolean NetAmountIsKey() {
			return false;
		}

		public Integer NetAmountLength() {
			return 18;
		}

		public Integer NetAmountPrecision() {
			return 13;
		}

		public String NetAmountDefault() {

			return null;

		}

		public String NetAmountComment() {

			return "";

		}

		public String NetAmountPattern() {

			return "dd-MM-yyyy";

		}

		public String NetAmountOriginalDbColumnName() {

			return "NetAmount";

		}

		public Float StockReceived;

		public Float getStockReceived() {
			return this.StockReceived;
		}

		public Boolean StockReceivedIsNullable() {
			return true;
		}

		public Boolean StockReceivedIsKey() {
			return false;
		}

		public Integer StockReceivedLength() {
			return 18;
		}

		public Integer StockReceivedPrecision() {
			return 16;
		}

		public String StockReceivedDefault() {

			return null;

		}

		public String StockReceivedComment() {

			return "";

		}

		public String StockReceivedPattern() {

			return "dd-MM-yyyy";

		}

		public String StockReceivedOriginalDbColumnName() {

			return "StockReceived";

		}

		public Float StockSold;

		public Float getStockSold() {
			return this.StockSold;
		}

		public Boolean StockSoldIsNullable() {
			return true;
		}

		public Boolean StockSoldIsKey() {
			return false;
		}

		public Integer StockSoldLength() {
			return 18;
		}

		public Integer StockSoldPrecision() {
			return 13;
		}

		public String StockSoldDefault() {

			return null;

		}

		public String StockSoldComment() {

			return "";

		}

		public String StockSoldPattern() {

			return "dd-MM-yyyy";

		}

		public String StockSoldOriginalDbColumnName() {

			return "StockSold";

		}

		public Float StockOnHand;

		public Float getStockOnHand() {
			return this.StockOnHand;
		}

		public Boolean StockOnHandIsNullable() {
			return true;
		}

		public Boolean StockOnHandIsKey() {
			return false;
		}

		public Integer StockOnHandLength() {
			return 18;
		}

		public Integer StockOnHandPrecision() {
			return 13;
		}

		public String StockOnHandDefault() {

			return null;

		}

		public String StockOnHandComment() {

			return "";

		}

		public String StockOnHandPattern() {

			return "dd-MM-yyyy";

		}

		public String StockOnHandOriginalDbColumnName() {

			return "StockOnHand";

		}

		public Integer Column22;

		public Integer getColumn22() {
			return this.Column22;
		}

		public Boolean Column22IsNullable() {
			return true;
		}

		public Boolean Column22IsKey() {
			return false;
		}

		public Integer Column22Length() {
			return 3;
		}

		public Integer Column22Precision() {
			return 0;
		}

		public String Column22Default() {

			return null;

		}

		public String Column22Comment() {

			return "";

		}

		public String Column22Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column22OriginalDbColumnName() {

			return "Column22";

		}

		public String Column23;

		public String getColumn23() {
			return this.Column23;
		}

		public Boolean Column23IsNullable() {
			return true;
		}

		public Boolean Column23IsKey() {
			return false;
		}

		public Integer Column23Length() {
			return 3;
		}

		public Integer Column23Precision() {
			return 0;
		}

		public String Column23Default() {

			return null;

		}

		public String Column23Comment() {

			return "";

		}

		public String Column23Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column23OriginalDbColumnName() {

			return "Column23";

		}

		public String Column24;

		public String getColumn24() {
			return this.Column24;
		}

		public Boolean Column24IsNullable() {
			return true;
		}

		public Boolean Column24IsKey() {
			return false;
		}

		public Integer Column24Length() {
			return 3;
		}

		public Integer Column24Precision() {
			return 0;
		}

		public String Column24Default() {

			return null;

		}

		public String Column24Comment() {

			return "";

		}

		public String Column24Pattern() {

			return "dd-MM-yyyy";

		}

		public String Column24OriginalDbColumnName() {

			return "Column24";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_OPTMIZE_E_COMMERCE_aa.length) {
					if (length < 1024 && commonByteArray_OPTMIZE_E_COMMERCE_aa.length == 0) {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[1024];
					} else {
						commonByteArray_OPTMIZE_E_COMMERCE_aa = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length);
				strReturn = new String(commonByteArray_OPTMIZE_E_COMMERCE_aa, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_OPTMIZE_E_COMMERCE_aa) {

				try {

					int length = 0;

					this.Date = readDate(dis);

					this.ProductName = readString(dis);

					this.ProductCategory = readString(dis);

					this.ProductSubCategory = readString(dis);

					this.ProductPrice = readString(dis);

					this.CustomerName = readString(dis);

					this.CustomerEmail = readString(dis);

					this.CustomerAddress = readString(dis);

					this.CustomerPhone = readString(dis);

					this.CustomerSegment = readString(dis);

					this.SupplierName = readString(dis);

					this.SupplierLocation = readString(dis);

					this.SupplierContact = readString(dis);

					this.ShipperName = readString(dis);

					this.ShippingMethod = readString(dis);

					this.QuantitySold = readString(dis);

					this.TotalAmount = readString(dis);

					this.DiscountAmount = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NetAmount = null;
					} else {
						this.NetAmount = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockReceived = null;
					} else {
						this.StockReceived = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockSold = null;
					} else {
						this.StockSold = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.StockOnHand = null;
					} else {
						this.StockOnHand = dis.readFloat();
					}

					this.Column22 = readInteger(dis);

					this.Column23 = readString(dis);

					this.Column24 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.Date, dos);

				// String

				writeString(this.ProductName, dos);

				// String

				writeString(this.ProductCategory, dos);

				// String

				writeString(this.ProductSubCategory, dos);

				// String

				writeString(this.ProductPrice, dos);

				// String

				writeString(this.CustomerName, dos);

				// String

				writeString(this.CustomerEmail, dos);

				// String

				writeString(this.CustomerAddress, dos);

				// String

				writeString(this.CustomerPhone, dos);

				// String

				writeString(this.CustomerSegment, dos);

				// String

				writeString(this.SupplierName, dos);

				// String

				writeString(this.SupplierLocation, dos);

				// String

				writeString(this.SupplierContact, dos);

				// String

				writeString(this.ShipperName, dos);

				// String

				writeString(this.ShippingMethod, dos);

				// String

				writeString(this.QuantitySold, dos);

				// String

				writeString(this.TotalAmount, dos);

				// String

				writeString(this.DiscountAmount, dos);

				// Float

				if (this.NetAmount == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.NetAmount);
				}

				// Float

				if (this.StockReceived == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockReceived);
				}

				// Float

				if (this.StockSold == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockSold);
				}

				// Float

				if (this.StockOnHand == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.StockOnHand);
				}

				// Integer

				writeInteger(this.Column22, dos);

				// String

				writeString(this.Column23, dos);

				// String

				writeString(this.Column24, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Date=" + String.valueOf(Date));
			sb.append(",ProductName=" + ProductName);
			sb.append(",ProductCategory=" + ProductCategory);
			sb.append(",ProductSubCategory=" + ProductSubCategory);
			sb.append(",ProductPrice=" + ProductPrice);
			sb.append(",CustomerName=" + CustomerName);
			sb.append(",CustomerEmail=" + CustomerEmail);
			sb.append(",CustomerAddress=" + CustomerAddress);
			sb.append(",CustomerPhone=" + CustomerPhone);
			sb.append(",CustomerSegment=" + CustomerSegment);
			sb.append(",SupplierName=" + SupplierName);
			sb.append(",SupplierLocation=" + SupplierLocation);
			sb.append(",SupplierContact=" + SupplierContact);
			sb.append(",ShipperName=" + ShipperName);
			sb.append(",ShippingMethod=" + ShippingMethod);
			sb.append(",QuantitySold=" + QuantitySold);
			sb.append(",TotalAmount=" + TotalAmount);
			sb.append(",DiscountAmount=" + DiscountAmount);
			sb.append(",NetAmount=" + String.valueOf(NetAmount));
			sb.append(",StockReceived=" + String.valueOf(StockReceived));
			sb.append(",StockSold=" + String.valueOf(StockSold));
			sb.append(",StockOnHand=" + String.valueOf(StockOnHand));
			sb.append(",Column22=" + String.valueOf(Column22));
			sb.append(",Column23=" + Column23);
			sb.append(",Column24=" + Column24);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row3Struct row3 = new row3Struct();
				row2Struct row2 = new row2Struct();
				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:\\Users\\YouCode\\Desktop\\optimize e-Commerce data\\data\\data.csv")).getAbsolutePath()
						.replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.exists()) {
					isFileGenerated_tFileOutputDelimited_1 = false;
				}
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						","/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, true), "UTF-8"));
				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("Date");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ProductName");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ProductCategory");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ProductSubCategory");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ProductPrice");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CustomerName");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CustomerEmail");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CustomerAddress");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CustomerPhone");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CustomerSegment");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SupplierName");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SupplierLocation");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("SupplierContact");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ShipperName");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ShippingMethod");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("QuantitySold");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("TotalAmount");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DiscountAmount");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("NetAmount");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("StockReceived");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("StockSold");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("StockOnHand");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Column22");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Column23");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Column24");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tSampleRow_1 begin ] start
				 */

				ok_Hash.put("tSampleRow_1", false);
				start_Hash.put("tSampleRow_1", System.currentTimeMillis());

				currentComponent = "tSampleRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tSampleRow_1 = 0;

				String[] rangetSampleRow_1 = "1..2505".split(",");
				java.util.Set rangeSettSampleRow_1 = new java.util.HashSet();

				Integer nb_line_tSampleRow_1 = 0;

				for (int i = 0; i < rangetSampleRow_1.length; i++) {

					if (rangetSampleRow_1[i].matches("\\d+")) {

						rangeSettSampleRow_1.add(Integer.valueOf(rangetSampleRow_1[i]));

					} else if (rangetSampleRow_1[i].matches("\\d+\\.\\.\\d+")) {

						String[] edgetSampleRow_1 = rangetSampleRow_1[i].split("\\.\\.");

						for (int j = Integer.valueOf(edgetSampleRow_1[0]).intValue(); j < Integer
								.valueOf(edgetSampleRow_1[1]).intValue() + 1; j++) {
							rangeSettSampleRow_1.add(Integer.valueOf(j));
						}
					} else {

					}

				}

				/**
				 * [tSampleRow_1 begin ] stop
				 */

				/**
				 * [tFileOutputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileOutputJSON_1", false);
				start_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileOutputJSON_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tFileOutputJSON_1 = 0;

				int nb_line_tFileOutputJSON_1 = 0;
				java.io.File file_tFileOutputJSON_1 = new java.io.File(
						"C:/Users/YouCode/Desktop/optimize e-Commerce data/data/data.json");
				java.io.File dir_tFileOutputJSON_1 = file_tFileOutputJSON_1.getParentFile();
				if (dir_tFileOutputJSON_1 != null && !dir_tFileOutputJSON_1.exists()) {
					dir_tFileOutputJSON_1.mkdirs();
				}
				java.io.PrintWriter outtFileOutputJSON_1 = new java.io.PrintWriter(new java.io.BufferedWriter(
						new java.io.FileWriter("C:/Users/YouCode/Desktop/optimize e-Commerce data/data/data.json")));
				outtFileOutputJSON_1.append("{\"" + "data" + "\":[");
				boolean isFirst_tFileOutputJSON_1 = true;

				/**
				 * [tFileOutputJSON_1 begin ] stop
				 */

				/**
				 * [tSampleRow_2 begin ] start
				 */

				ok_Hash.put("tSampleRow_2", false);
				start_Hash.put("tSampleRow_2", System.currentTimeMillis());

				currentComponent = "tSampleRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tSampleRow_2 = 0;

				String[] rangetSampleRow_2 = "2506..5011".split(",");
				java.util.Set rangeSettSampleRow_2 = new java.util.HashSet();

				Integer nb_line_tSampleRow_2 = 0;

				for (int i = 0; i < rangetSampleRow_2.length; i++) {

					if (rangetSampleRow_2[i].matches("\\d+")) {

						rangeSettSampleRow_2.add(Integer.valueOf(rangetSampleRow_2[i]));

					} else if (rangetSampleRow_2[i].matches("\\d+\\.\\.\\d+")) {

						String[] edgetSampleRow_2 = rangetSampleRow_2[i].split("\\.\\.");

						for (int j = Integer.valueOf(edgetSampleRow_2[0]).intValue(); j < Integer
								.valueOf(edgetSampleRow_2[1]).intValue() + 1; j++) {
							rangeSettSampleRow_2.add(Integer.valueOf(j));
						}
					} else {

					}

				}

				/**
				 * [tSampleRow_2 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/YouCode/Desktop/optimize e-Commerce data/ecommerce_data.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/YouCode/Desktop/optimize e-Commerce data/ecommerce_data.csv", "US-ASCII", ",",
								"\n", false, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Date = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Date", "row1", temp, ex_tFileInputDelimited_1), ex_tFileInputDelimited_1));
								}

							} else {

								row1.Date = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row1.ProductName = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.ProductCategory = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.ProductSubCategory = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.ProductPrice = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.CustomerName = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.CustomerEmail = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							row1.CustomerAddress = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row1.CustomerPhone = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.CustomerSegment = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row1.SupplierName = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.SupplierLocation = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							row1.SupplierContact = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row1.ShipperName = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							row1.ShippingMethod = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 15;

							row1.QuantitySold = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 16;

							row1.TotalAmount = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 17;

							row1.DiscountAmount = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 18;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.NetAmount = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"NetAmount", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.NetAmount = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 19;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.StockReceived = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"StockReceived", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.StockReceived = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 20;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.StockSold = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"StockSold", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.StockSold = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 21;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.StockOnHand = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"StockOnHand", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.StockOnHand = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 22;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Column22 = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Column22", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Column22 = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 23;

							row1.Column23 = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 24;

							row1.Column24 = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tReplicate_1 main ] start
							 */

							currentComponent = "tReplicate_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row3 = new row3Struct();

							row3.Date = row1.Date;
							row3.ProductName = row1.ProductName;
							row3.ProductCategory = row1.ProductCategory;
							row3.ProductSubCategory = row1.ProductSubCategory;
							row3.ProductPrice = row1.ProductPrice;
							row3.CustomerName = row1.CustomerName;
							row3.CustomerEmail = row1.CustomerEmail;
							row3.CustomerAddress = row1.CustomerAddress;
							row3.CustomerPhone = row1.CustomerPhone;
							row3.CustomerSegment = row1.CustomerSegment;
							row3.SupplierName = row1.SupplierName;
							row3.SupplierLocation = row1.SupplierLocation;
							row3.SupplierContact = row1.SupplierContact;
							row3.ShipperName = row1.ShipperName;
							row3.ShippingMethod = row1.ShippingMethod;
							row3.QuantitySold = row1.QuantitySold;
							row3.TotalAmount = row1.TotalAmount;
							row3.DiscountAmount = row1.DiscountAmount;
							row3.NetAmount = row1.NetAmount;
							row3.StockReceived = row1.StockReceived;
							row3.StockSold = row1.StockSold;
							row3.StockOnHand = row1.StockOnHand;
							row3.Column22 = row1.Column22;
							row3.Column23 = row1.Column23;
							row3.Column24 = row1.Column24;
							row4 = new row4Struct();

							row4.Date = row1.Date;
							row4.ProductName = row1.ProductName;
							row4.ProductCategory = row1.ProductCategory;
							row4.ProductSubCategory = row1.ProductSubCategory;
							row4.ProductPrice = row1.ProductPrice;
							row4.CustomerName = row1.CustomerName;
							row4.CustomerEmail = row1.CustomerEmail;
							row4.CustomerAddress = row1.CustomerAddress;
							row4.CustomerPhone = row1.CustomerPhone;
							row4.CustomerSegment = row1.CustomerSegment;
							row4.SupplierName = row1.SupplierName;
							row4.SupplierLocation = row1.SupplierLocation;
							row4.SupplierContact = row1.SupplierContact;
							row4.ShipperName = row1.ShipperName;
							row4.ShippingMethod = row1.ShippingMethod;
							row4.QuantitySold = row1.QuantitySold;
							row4.TotalAmount = row1.TotalAmount;
							row4.DiscountAmount = row1.DiscountAmount;
							row4.NetAmount = row1.NetAmount;
							row4.StockReceived = row1.StockReceived;
							row4.StockSold = row1.StockSold;
							row4.StockOnHand = row1.StockOnHand;
							row4.Column22 = row1.Column22;
							row4.Column23 = row1.Column23;
							row4.Column24 = row1.Column24;

							tos_count_tReplicate_1++;

							/**
							 * [tReplicate_1 main ] stop
							 */

							/**
							 * [tReplicate_1 process_data_begin ] start
							 */

							currentComponent = "tReplicate_1";

							/**
							 * [tReplicate_1 process_data_begin ] stop
							 */

							/**
							 * [tSampleRow_1 main ] start
							 */

							currentComponent = "tSampleRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row3"

								);
							}

							nb_line_tSampleRow_1++;

							if (!rangeSettSampleRow_1.contains(nb_line_tSampleRow_1)) {
								row2 = null;
							} else {
								row2 = new row2Struct();

								row2.Date = row3.Date;

								row2.ProductName = row3.ProductName;

								row2.ProductCategory = row3.ProductCategory;

								row2.ProductSubCategory = row3.ProductSubCategory;

								row2.ProductPrice = row3.ProductPrice;

								row2.CustomerName = row3.CustomerName;

								row2.CustomerEmail = row3.CustomerEmail;

								row2.CustomerAddress = row3.CustomerAddress;

								row2.CustomerPhone = row3.CustomerPhone;

								row2.CustomerSegment = row3.CustomerSegment;

								row2.SupplierName = row3.SupplierName;

								row2.SupplierLocation = row3.SupplierLocation;

								row2.SupplierContact = row3.SupplierContact;

								row2.ShipperName = row3.ShipperName;

								row2.ShippingMethod = row3.ShippingMethod;

								row2.QuantitySold = row3.QuantitySold;

								row2.TotalAmount = row3.TotalAmount;

								row2.DiscountAmount = row3.DiscountAmount;

								row2.NetAmount = row3.NetAmount;

								row2.StockReceived = row3.StockReceived;

								row2.StockSold = row3.StockSold;

								row2.StockOnHand = row3.StockOnHand;

								row2.Column22 = row3.Column22;

								row2.Column23 = row3.Column23;

								row2.Column24 = row3.Column24;

							}

							tos_count_tSampleRow_1++;

							/**
							 * [tSampleRow_1 main ] stop
							 */

							/**
							 * [tSampleRow_1 process_data_begin ] start
							 */

							currentComponent = "tSampleRow_1";

							/**
							 * [tSampleRow_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								if (row2.Date != null) {
									sb_tFileOutputDelimited_1
											.append(FormatterUtils.format_Date(row2.Date, "dd-MM-yyyy"));
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ProductName != null) {
									sb_tFileOutputDelimited_1.append(row2.ProductName);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ProductCategory != null) {
									sb_tFileOutputDelimited_1.append(row2.ProductCategory);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ProductSubCategory != null) {
									sb_tFileOutputDelimited_1.append(row2.ProductSubCategory);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ProductPrice != null) {
									sb_tFileOutputDelimited_1.append(row2.ProductPrice);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.CustomerName != null) {
									sb_tFileOutputDelimited_1.append(row2.CustomerName);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.CustomerEmail != null) {
									sb_tFileOutputDelimited_1.append(row2.CustomerEmail);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.CustomerAddress != null) {
									sb_tFileOutputDelimited_1.append(row2.CustomerAddress);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.CustomerPhone != null) {
									sb_tFileOutputDelimited_1.append(row2.CustomerPhone);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.CustomerSegment != null) {
									sb_tFileOutputDelimited_1.append(row2.CustomerSegment);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.SupplierName != null) {
									sb_tFileOutputDelimited_1.append(row2.SupplierName);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.SupplierLocation != null) {
									sb_tFileOutputDelimited_1.append(row2.SupplierLocation);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.SupplierContact != null) {
									sb_tFileOutputDelimited_1.append(row2.SupplierContact);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ShipperName != null) {
									sb_tFileOutputDelimited_1.append(row2.ShipperName);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.ShippingMethod != null) {
									sb_tFileOutputDelimited_1.append(row2.ShippingMethod);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.QuantitySold != null) {
									sb_tFileOutputDelimited_1.append(row2.QuantitySold);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.TotalAmount != null) {
									sb_tFileOutputDelimited_1.append(row2.TotalAmount);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.DiscountAmount != null) {
									sb_tFileOutputDelimited_1.append(row2.DiscountAmount);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.NetAmount != null) {
									sb_tFileOutputDelimited_1.append(row2.NetAmount);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.StockReceived != null) {
									sb_tFileOutputDelimited_1.append(row2.StockReceived);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.StockSold != null) {
									sb_tFileOutputDelimited_1.append(row2.StockSold);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.StockOnHand != null) {
									sb_tFileOutputDelimited_1.append(row2.StockOnHand);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.Column22 != null) {
									sb_tFileOutputDelimited_1.append(row2.Column22);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.Column23 != null) {
									sb_tFileOutputDelimited_1.append(row2.Column23);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row2.Column24 != null) {
									sb_tFileOutputDelimited_1.append(row2.Column24);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

							} // End of branch "row2"

							/**
							 * [tSampleRow_1 process_data_end ] start
							 */

							currentComponent = "tSampleRow_1";

							/**
							 * [tSampleRow_1 process_data_end ] stop
							 */

							/**
							 * [tSampleRow_2 main ] start
							 */

							currentComponent = "tSampleRow_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							nb_line_tSampleRow_2++;

							if (!rangeSettSampleRow_2.contains(nb_line_tSampleRow_2)) {
								row5 = null;
							} else {
								row5 = new row5Struct();

								row5.Date = row4.Date;

								row5.ProductName = row4.ProductName;

								row5.ProductCategory = row4.ProductCategory;

								row5.ProductSubCategory = row4.ProductSubCategory;

								row5.ProductPrice = row4.ProductPrice;

								row5.CustomerName = row4.CustomerName;

								row5.CustomerEmail = row4.CustomerEmail;

								row5.CustomerAddress = row4.CustomerAddress;

								row5.CustomerPhone = row4.CustomerPhone;

								row5.CustomerSegment = row4.CustomerSegment;

								row5.SupplierName = row4.SupplierName;

								row5.SupplierLocation = row4.SupplierLocation;

								row5.SupplierContact = row4.SupplierContact;

								row5.ShipperName = row4.ShipperName;

								row5.ShippingMethod = row4.ShippingMethod;

								row5.QuantitySold = row4.QuantitySold;

								row5.TotalAmount = row4.TotalAmount;

								row5.DiscountAmount = row4.DiscountAmount;

								row5.NetAmount = row4.NetAmount;

								row5.StockReceived = row4.StockReceived;

								row5.StockSold = row4.StockSold;

								row5.StockOnHand = row4.StockOnHand;

								row5.Column22 = row4.Column22;

								row5.Column23 = row4.Column23;

								row5.Column24 = row4.Column24;

							}

							tos_count_tSampleRow_2++;

							/**
							 * [tSampleRow_2 main ] stop
							 */

							/**
							 * [tSampleRow_2 process_data_begin ] start
							 */

							currentComponent = "tSampleRow_2";

							/**
							 * [tSampleRow_2 process_data_begin ] stop
							 */
// Start of branch "row5"
							if (row5 != null) {

								/**
								 * [tFileOutputJSON_1 main ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

								org.json.simple.JSONObject jsonRowtFileOutputJSON_1 = new org.json.simple.JSONObject();
								if (row5.Date != null) {

									jsonRowtFileOutputJSON_1.put("Date",
											FormatterUtils.format_Date(row5.Date, "dd-MM-yyyy"));

								} else {
									jsonRowtFileOutputJSON_1.put("Date", null);
								}

								if (row5.ProductName != null) {

									jsonRowtFileOutputJSON_1.put("ProductName", row5.ProductName);

								} else {
									jsonRowtFileOutputJSON_1.put("ProductName", null);
								}

								if (row5.ProductCategory != null) {

									jsonRowtFileOutputJSON_1.put("ProductCategory", row5.ProductCategory);

								} else {
									jsonRowtFileOutputJSON_1.put("ProductCategory", null);
								}

								if (row5.ProductSubCategory != null) {

									jsonRowtFileOutputJSON_1.put("ProductSubCategory", row5.ProductSubCategory);

								} else {
									jsonRowtFileOutputJSON_1.put("ProductSubCategory", null);
								}

								if (row5.ProductPrice != null) {

									jsonRowtFileOutputJSON_1.put("ProductPrice", row5.ProductPrice);

								} else {
									jsonRowtFileOutputJSON_1.put("ProductPrice", null);
								}

								if (row5.CustomerName != null) {

									jsonRowtFileOutputJSON_1.put("CustomerName", row5.CustomerName);

								} else {
									jsonRowtFileOutputJSON_1.put("CustomerName", null);
								}

								if (row5.CustomerEmail != null) {

									jsonRowtFileOutputJSON_1.put("CustomerEmail", row5.CustomerEmail);

								} else {
									jsonRowtFileOutputJSON_1.put("CustomerEmail", null);
								}

								if (row5.CustomerAddress != null) {

									jsonRowtFileOutputJSON_1.put("CustomerAddress", row5.CustomerAddress);

								} else {
									jsonRowtFileOutputJSON_1.put("CustomerAddress", null);
								}

								if (row5.CustomerPhone != null) {

									jsonRowtFileOutputJSON_1.put("CustomerPhone", row5.CustomerPhone);

								} else {
									jsonRowtFileOutputJSON_1.put("CustomerPhone", null);
								}

								if (row5.CustomerSegment != null) {

									jsonRowtFileOutputJSON_1.put("CustomerSegment", row5.CustomerSegment);

								} else {
									jsonRowtFileOutputJSON_1.put("CustomerSegment", null);
								}

								if (row5.SupplierName != null) {

									jsonRowtFileOutputJSON_1.put("SupplierName", row5.SupplierName);

								} else {
									jsonRowtFileOutputJSON_1.put("SupplierName", null);
								}

								if (row5.SupplierLocation != null) {

									jsonRowtFileOutputJSON_1.put("SupplierLocation", row5.SupplierLocation);

								} else {
									jsonRowtFileOutputJSON_1.put("SupplierLocation", null);
								}

								if (row5.SupplierContact != null) {

									jsonRowtFileOutputJSON_1.put("SupplierContact", row5.SupplierContact);

								} else {
									jsonRowtFileOutputJSON_1.put("SupplierContact", null);
								}

								if (row5.ShipperName != null) {

									jsonRowtFileOutputJSON_1.put("ShipperName", row5.ShipperName);

								} else {
									jsonRowtFileOutputJSON_1.put("ShipperName", null);
								}

								if (row5.ShippingMethod != null) {

									jsonRowtFileOutputJSON_1.put("ShippingMethod", row5.ShippingMethod);

								} else {
									jsonRowtFileOutputJSON_1.put("ShippingMethod", null);
								}

								if (row5.QuantitySold != null) {

									jsonRowtFileOutputJSON_1.put("QuantitySold", row5.QuantitySold);

								} else {
									jsonRowtFileOutputJSON_1.put("QuantitySold", null);
								}

								if (row5.TotalAmount != null) {

									jsonRowtFileOutputJSON_1.put("TotalAmount", row5.TotalAmount);

								} else {
									jsonRowtFileOutputJSON_1.put("TotalAmount", null);
								}

								if (row5.DiscountAmount != null) {

									jsonRowtFileOutputJSON_1.put("DiscountAmount", row5.DiscountAmount);

								} else {
									jsonRowtFileOutputJSON_1.put("DiscountAmount", null);
								}

								if (row5.NetAmount != null) {

									jsonRowtFileOutputJSON_1.put("NetAmount", row5.NetAmount);

								} else {
									jsonRowtFileOutputJSON_1.put("NetAmount", null);
								}

								if (row5.StockReceived != null) {

									jsonRowtFileOutputJSON_1.put("StockReceived", row5.StockReceived);

								} else {
									jsonRowtFileOutputJSON_1.put("StockReceived", null);
								}

								if (row5.StockSold != null) {

									jsonRowtFileOutputJSON_1.put("StockSold", row5.StockSold);

								} else {
									jsonRowtFileOutputJSON_1.put("StockSold", null);
								}

								if (row5.StockOnHand != null) {

									jsonRowtFileOutputJSON_1.put("StockOnHand", row5.StockOnHand);

								} else {
									jsonRowtFileOutputJSON_1.put("StockOnHand", null);
								}

								if (row5.Column22 != null) {

									jsonRowtFileOutputJSON_1.put("Column22", row5.Column22);

								} else {
									jsonRowtFileOutputJSON_1.put("Column22", null);
								}

								if (row5.Column23 != null) {

									jsonRowtFileOutputJSON_1.put("Column23", row5.Column23);

								} else {
									jsonRowtFileOutputJSON_1.put("Column23", null);
								}

								if (row5.Column24 != null) {

									jsonRowtFileOutputJSON_1.put("Column24", row5.Column24);

								} else {
									jsonRowtFileOutputJSON_1.put("Column24", null);
								}

								if (!isFirst_tFileOutputJSON_1) {
									outtFileOutputJSON_1.append(",");
								}
								isFirst_tFileOutputJSON_1 = false;
								outtFileOutputJSON_1.append(jsonRowtFileOutputJSON_1.toJSONString());
								nb_line_tFileOutputJSON_1++;

								tos_count_tFileOutputJSON_1++;

								/**
								 * [tFileOutputJSON_1 main ] stop
								 */

								/**
								 * [tFileOutputJSON_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								/**
								 * [tFileOutputJSON_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputJSON_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputJSON_1";

								/**
								 * [tFileOutputJSON_1 process_data_end ] stop
								 */

							} // End of branch "row5"

							/**
							 * [tSampleRow_2 process_data_end ] start
							 */

							currentComponent = "tSampleRow_2";

							/**
							 * [tSampleRow_2 process_data_end ] stop
							 */

							/**
							 * [tReplicate_1 process_data_end ] start
							 */

							currentComponent = "tReplicate_1";

							/**
							 * [tReplicate_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Users/YouCode/Desktop/optimize e-Commerce data/ecommerce_data.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tSampleRow_1 end ] start
				 */

				currentComponent = "tSampleRow_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tSampleRow_1", true);
				end_Hash.put("tSampleRow_1", System.currentTimeMillis());

				/**
				 * [tSampleRow_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tSampleRow_2 end ] start
				 */

				currentComponent = "tSampleRow_2";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tSampleRow_2", true);
				end_Hash.put("tSampleRow_2", System.currentTimeMillis());

				/**
				 * [tSampleRow_2 end ] stop
				 */

				/**
				 * [tFileOutputJSON_1 end ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				outtFileOutputJSON_1.print("]}");
				outtFileOutputJSON_1.close();
				globalMap.put("tFileOutputJSON_1_NB_LINE", nb_line_tFileOutputJSON_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tFileOutputJSON_1", true);
				end_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileOutputJSON_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tSampleRow_1 finally ] start
				 */

				currentComponent = "tSampleRow_1";

				/**
				 * [tSampleRow_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tSampleRow_2 finally ] start
				 */

				currentComponent = "tSampleRow_2";

				/**
				 * [tSampleRow_2 finally ] stop
				 */

				/**
				 * [tFileOutputJSON_1 finally ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				/**
				 * [tFileOutputJSON_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final aa aaClass = new aa();

		int exitCode = aaClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

			}
		}
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = aa.class.getClassLoader()
					.getResourceAsStream("optmize_e_commerce/aa_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = aa.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("output", "id_String");
					if (context.getStringValue("output") == null) {
						context.output = null;
					} else {
						context.output = (String) context.getProperty("output");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("output")) {
				context.output = (String) parentContextMap.get("output");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : aa");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 263475 characters generated by Talaxie Open Studio for Data Integration on
 * the 20 janvier 2025, 20:34:26 UTC+01:00
 ************************************************************************************************/