DROP TABLE IF EXISTS ENTRYRAWTRANSACTION;

DROP TABLE IF EXISTS EXITFOREIGNOTHERTRANS;
DROP TABLE IF EXISTS EXITFOREIGNETCTRANS;

DROP TABLE IF EXISTS EXITLOCALETCTRANS;
DROP TABLE IF EXISTS EXITLOCALOTHERTRANS;
DROP TABLE IF EXISTS EXDFOREIGNGASTRANSACTION;
DROP TABLE IF EXISTS EXDFOREIGNMUNICIPALTRANSACTION;
DROP TABLE IF EXISTS EXDFOREIGNPARKTRANSACTION;
DROP TABLE IF EXISTS EXDLOCALTRANSACTION;
DROP TABLE IF EXISTS GANTRYCPCTRANSACTION;
DROP TABLE IF EXISTS GANTRYETCTRANSACTION;
DROP TABLE IF EXISTS TOLLCHANGETRANSACTIONS;

DROP TABLE IF EXISTS ETCSPLITRESULTEXIT;
DROP TABLE IF EXISTS ETCSPLITRESULTGANTRY;
DROP TABLE IF EXISTS OTHERSPLITRESULTGANTRY;
DROP TABLE IF EXISTS OTHERSPLITRESULTEXIT;

DROP TABLE IF EXISTS ETCCLEARRESULT;
DROP TABLE IF EXISTS CASHCLEARRESULT;
DROP TABLE IF EXISTS EXPANDCLEARRESULT;


CREATE TABLE ENTRYRAWTRANSACTION(ID text,TRANSCODE text,BL_SUBCENTER text,BL_CENTER text,LDATE text,SHIFT text,BATCHNUM text,LOGINTIME text,TRIGGERTIME text,OPERID text,OPERNAME text,MONITOR text,MONITORNAME text,MONITORTIME text,LANEAPPVER text,LANETYPE bigint,ENTOLLSTATION text,ENTOLLLANE text,ENTOLLSTATIONHEX text,ENTOLLLANEHEX text,ENTOLLSTATIONID text,ENTOLLLANEID text,ENTIME text,MEDIATYPE bigint,OBUSIGN bigint,OBUISSUEFLAG text,OBUID text,SUPPLIERID bigint,VCOUNT bigint,BALANCEBEFORE text,TRANSFEE bigint,CARDTYPE text,CARDNET text,CARDID text,CARDBOX bigint,CARDCOUNT bigint,CARDSN bigint,CARDCNT bigint,INDUCTCNT bigint,VLP text,VLPC bigint,IDENTIFYVLP text,IDENTIFYVLPC bigint,VEHICLETYPE bigint,VEHICLECLASS bigint,TAC text,TRANSTYPE text,TERMINALNO text,ENWEIGHT bigint,AXISINFO bigint,LIMITWEIGHT bigint,OVERWEIGHTRATE bigint,ENAXLECOUNT bigint,ELECTRICALPERCENTAGE bigint,SIGNSTATUS bigint,DESCRIPTION bigint,PARAVER text,KEYNUM bigint,KEYPRESSINFO text,SPECIALTYPE text,LANESPINFO text,SPINFO text,VEHICLESIGNID text,PASSID text,VERIFYCODE text,OBUMAC text,OBUSN text,KEYVERSION bigint,DIRECTION bigint,STATIONRECEIVETIME text,VERIFYFLAG bigint,TRANSFLAG bigint,REMARKS text,RECEIVETIME text,SPARE1 text,SPARE2 text,SPARE3 text,SPARE4 bigint,SPARE5 text,VERIFYPASSTIME text,VEHICLESIGN text,TERMINALTRANSNO text,OBUVERSION bigint,CARDVERSION bigint,OPERATIONMEDIA bigint,CHARGEMODE bigint,WASTESPARE4 text,WASTESPARE5 text,WASTESPARE1 text,WASTESPARE2 text,WASTESPARE3 text);

CREATE TABLE EXITFOREIGNETCTRANS(ID text,MODIFYFLAG bigint,MULTIPROVINCE bigint,MEDIATYPE bigint,MEDIANO bigint,TRANSPAYTYPE bigint,TOLLPROVINCEID bigint,ISSUERID bigint,OBUSIGN text,IDENTIFICATION bigint,OBUID text,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,EXTIME text,VLP text,VLPC bigint,IDENTIFYVLP text,IDENTIFYVLPC bigint,VEHICLESIGNID text,VEHICLETYPE bigint,VEHICLECLASS bigint,TAC text,TRANSFEE bigint,TRANSTYPE text,PAYCARDTRANSN bigint,TERMINALTRANSNO text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,DESCRIPTION bigint,LANESPINFO text,SPINFO text,SPECIALTYPE text,ENTOLLSTATIONNAME text,ENTOLLSTATION bigint,ENTOLLLANE text,ENTOLLSTATIONID text,ENTOLLLANEID text,ENTOLLSTATIONHEX text,ENTOLLLANEHEX text,ENVLP text,ENVLPC bigint,ENTIME text,ENWEIGHT bigint,ENAXLECOUNT bigint,EXTOLLSTATIONNAME text,EXTOLLSTATION text,EXTOLLLANE text,EXTOLLSTATIONID text,EXTOLLLANEID text,EXTOLLSTATIONHEX text,EXTOLLLANEHEX text,EXWEIGHT bigint,AXLECOUNT bigint,CARDCOSTFEE bigint,UNPAYFEE bigint,UNPAYFLAG bigint,UNPAYCARDCOST bigint,TICKETFEE bigint,UNIFIEDFEE bigint,TRANSCODE text,SHIFT bigint,OPERID bigint,OPERNAME text,LANEAPPVER text,ELECTRICALPERCENTAGE bigint,PARAVER text,CHECKSIGN bigint,OPEN bigint,LANETYPE bigint,BL_SUBCENTER bigint,BL_CENTER bigint,LDATE text,BATCHNUM text,PASSID text,VEHICLESIGN text,PAYFEE bigint,FEE bigint,DISCOUNTFEE bigint,NOCARDCOUNT bigint,FEEMILEAGE bigint,SHORTFEE bigint,SHORTFEEMILEAGE bigint,EXITFEETYPE bigint,SPCRATEVERSION bigint,CARDTOTALAMOUNT bigint,OBUTOTALAMOUNT text,OBUTOTALDISCOUNTAMOUNT text,OBUPROVINCEFEE bigint,TOTALCOUNT bigint,PROVTRANSCOUNT bigint,PROVINCECOUNT bigint,DISCOUNTTYPE text,PROVINCEDISCOUNTFEE text,ORIGINFEE text,SIGNSTATUS bigint,ROADTYPE bigint,VERIFYPASSTIME text,STATIONRECEIVETIME text,RECEIVETIME text,GENTIME text,MESSAGEID text,REMARKS text,STATUS bigint,RESPONSECODE bigint,RESPONSEINFO text,NATIONRECEIVETIME text,PROTIME text,BATCHFILENAME text,CHARGESTATUS bigint,ISSUECHARGETIME text,CHARGERECEIVETIME text,CHARGETIME text,DISPUTEDTYPE text,DISPUTEDDESC text,DISPUTEDPROTIME text,DISPUTEDVERSION text,NATIONCLEARDATE text,NATIONCLEARFLAG bigint,NATIONCLEARTIME text,SPLITINFEE bigint,SPLITCROSSFEE bigint,NATIONSPLITTIME text,NATIONSPLITTYPE bigint,SPLITFLAG bigint,PROSPLITTIME text,PROSPLITTYPE bigint,SPLITOWNERCOUNT bigint,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITCHECKFLAG bigint,SPLITCNT bigint,SPLITFEE bigint,REALSPLITCNT bigint,SPLITREMARK text,CLEARDATE text,CLEARFLAG bigint,CLEARREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,CHARGEFEE bigint,CHARGEDISCOUNTTYPE text,CHARGEPROVINCEDISCOUNTFEE text,CHARGERORIGINFEE text,FEERATE bigint,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,SECFREETYPE bigint,SECPRORESULT bigint,SECPROTIME text,SECBEFFEE text,SECFREEFEE text,ORIGINALFLAG bigint,PACKTIME text,SPLITCHECKDATE text,SPLITCHECKSTATUS text,SPLITCHECKTIME text,VACCINEFLAG bigint,APPOINTID text,CHARGEAPPOINTID text);
CREATE TABLE EXITFOREIGNOTHERTRANS(ID text,WASTETYPE bigint,MODIFYFLAG bigint,MULTIPROVINCE bigint,MEDIATYPE bigint,MEDIANO bigint,EXITFEETYPE bigint,TOLLPROVINCEID bigint,OBUSIGN text,IDENTIFICATION bigint,ETCCARDTYPE text,CARDNET text,CARDID bigint,EXTIME text,VLP text,VLPC bigint,IDENTIFYVLP text,IDENTIFYVLPC bigint,VEHICLESIGNID text,VEHICLETYPE bigint,VEHICLECLASS bigint,DESCRIPTION bigint,LANESPINFO text,SPINFO text,SPECIALTYPE text,ENTOLLSTATIONNAME text,ENTOLLSTATION bigint,ENTOLLLANE text,ENTOLLSTATIONID text,ENTOLLLANEID text,ENTOLLSTATIONHEX text,ENTOLLLANEHEX text,ENVLP text,ENVLPC bigint,ENTIME text,ENWEIGHT bigint,ENAXLECOUNT bigint,EXTOLLSTATIONNAME text,EXTOLLSTATION text,EXTOLLLANE text,EXTOLLSTATIONID text,EXTOLLLANEID text,EXTOLLSTATIONHEX text,EXTOLLLANEHEX text,EXWEIGHT bigint,AXLECOUNT bigint,CARDCOSTFEE bigint,UNPAYFEE bigint,UNPAYFLAG bigint,UNPAYCARDCOST bigint,TICKETFEE bigint,UNIFIEDFEE bigint,TRANSCODE text,SHIFT bigint,OPERID text,OPERNAME text,LANEAPPVER text,ELECTRICALPERCENTAGE bigint,PARAVER text,CHECKSIGN bigint,LANETYPE bigint,OPEN bigint,BL_SUBCENTER text,BL_CENTER text,LDATE text,BATCHNUM text,PAKAGEID text,PAKAGETIME text,CHARGEBATCH text,PAYCARDTRANSN bigint,PAYORDERNUM text,PAYCODE text,VERIFYPASSTIME text,PASSID text,VEHICLESIGN text,PAYFEE bigint,FEE bigint,DISCOUNTFEE bigint,NOCARDCOUNT bigint,FEEMILEAGE bigint,SHORTFEE bigint,SHORTFEEMILEAGE bigint,FEERATE bigint,SPCRATEVERSION bigint,CARDTOTALAMOUNT bigint,OBUTOTALAMOUNT text,OBUTOTALDISCOUNTAMOUNT text,OBUPROVINCEFEE bigint,TOTALCOUNT bigint,PROVTRANSCOUNT bigint,PROVINCECOUNT bigint,DISCOUNTTYPE text,PROVINCEDISCOUNTFEE text,ORIGINFEE text,ROADTYPE bigint,PAYTYPE bigint,SIGNSTATUS bigint,GENTIME text,STATIONRECEIVETIME text,RECEIVETIME text,BATCHFILENAME text,STATUS bigint,PROTIME text,RESPONSECODE bigint,RESPONSEINFO text,NATIONRECEIVETIME text,SPLITCHECKDATE text,SPLITCHECKSTATUS bigint,SPLITCHECKTIME text,SPLITINFEE bigint,SPLITCROSSFEE bigint,NATIONSPLITTIME text,NATIONSPLITTYPE bigint,SPLITFLAG bigint,PROSPLITTIME text,PROSPLITTYPE bigint,SPLITOWNERCOUNT bigint,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITCHECKFLAG bigint,SPLITCNT bigint,SPLITFEE bigint,REALSPLITCNT bigint,SPLITREMARK text,CLEARDATE text,CLEARFLAG bigint,CLEARREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,CHARGERESULT bigint,ISSUECHARGETIME text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,ORIGINALFLAG bigint,VACCINEFLAG bigint,APPOINTID text);
CREATE TABLE EXITLOCALETCTRANS(ID text,MODIFYFLAG bigint,MULTIPROVINCE bigint,MEDIATYPE bigint,MEDIANO bigint,TRANSPAYTYPE bigint,TOLLPROVINCEID bigint,ISSUERID bigint,OBUSIGN text,IDENTIFICATION bigint,OBUID text,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,EXTIME text,VLP text,VLPC bigint,IDENTIFYVLP text,IDENTIFYVLPC bigint,VEHICLESIGNID text,VEHICLETYPE bigint,VEHICLECLASS bigint,TAC text,TRANSFEE bigint,TRANSTYPE text,PAYCARDTRANSN bigint,TERMINALTRANSNO text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,DESCRIPTION bigint,LANESPINFO text,SPINFO text,SPECIALTYPE text,ENTOLLSTATIONNAME text,ENTOLLSTATION text,ENTOLLLANE text,ENTOLLSTATIONID text,ENTOLLLANEID text,ENTOLLSTATIONHEX text,ENTOLLLANEHEX text,ENVLP text,ENVLPC bigint,ENTIME text,ENWEIGHT bigint,ENAXLECOUNT bigint,EXTOLLSTATIONNAME text,EXTOLLSTATION text,EXTOLLLANE text,EXTOLLSTATIONID text,EXTOLLLANEID text,EXTOLLSTATIONHEX text,EXTOLLLANEHEX text,EXWEIGHT bigint,AXLECOUNT bigint,CARDCOSTFEE bigint,UNPAYFEE bigint,UNPAYFLAG bigint,UNPAYCARDCOST bigint,TICKETFEE bigint,UNIFIEDFEE bigint,TRANSCODE text,SHIFT bigint,OPERID bigint,OPERNAME text,LANEAPPVER text,ELECTRICALPERCENTAGE bigint,PARAVER text,CHECKSIGN bigint,OPEN bigint,LANETYPE bigint,BL_SUBCENTER text,BL_CENTER text,LDATE text,BATCHNUM text,PASSID text,VEHICLESIGN text,PAYFEE bigint,FEE bigint,DISCOUNTFEE bigint,NOCARDCOUNT bigint,FEEMILEAGE bigint,SHORTFEE bigint,SHORTFEEMILEAGE bigint,EXITFEETYPE bigint,SPCRATEVERSION bigint,CARDTOTALAMOUNT bigint,OBUTOTALAMOUNT text,OBUTOTALDISCOUNTAMOUNT text,OBUPROVINCEFEE bigint,TOTALCOUNT bigint,PROVTRANSCOUNT bigint,PROVINCECOUNT bigint,DISCOUNTTYPE text,PROVINCEDISCOUNTFEE text,ORIGINFEE text,ROADTYPE bigint,VERIFYPASSTIME text,STATIONRECEIVETIME text,RECEIVETIME text,GENTIME text,CHARGESTATUS bigint,ISSUECHARGETIME text,CHARGEBATCH text,SIGNSTATUS bigint,STATUS bigint,RESPONSECODE bigint,RESPONSEINFO text,NATIONRECEIVETIME text,PROTIME text,BATCHFILENAME text,PACKSTATUS bigint,MESSAGEID text,NATIONCLEARDATE text,SPLITFLAG bigint,PROSPLITTIME text,PROSPLITTYPE bigint,SPLITOWNERCOUNT bigint,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITREMARK text,CLEARDATE text,CLEARFLAG bigint,CLEARREMARK text,REMARKS text,SPARE1 text,SPARE2 text,SPARE3 text,FEERATE bigint,FIRSTCLEARFLAG bigint,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,ORIGINALFLAG bigint,PACKTIME text,SPLITBASE bigint,SPLITREMARK1 text,VACCINEFLAG bigint,APPOINTID text);
CREATE TABLE EXITLOCALOTHERTRANS(ID text,WASTETYPE bigint,MODIFYFLAG bigint,MULTIPROVINCE bigint,MEDIATYPE bigint,MEDIANO bigint,EXITFEETYPE bigint,TOLLPROVINCEID bigint,OBUSIGN text,IDENTIFICATION bigint,ETCCARDTYPE text,CARDNET text,CARDID bigint,EXTIME text,VLP text,VLPC bigint,IDENTIFYVLP text,IDENTIFYVLPC bigint,VEHICLESIGNID text,VEHICLETYPE bigint,VEHICLECLASS bigint,DESCRIPTION bigint,LANESPINFO text,SPINFO text,SPECIALTYPE text,ENTOLLSTATIONNAME text,ENTOLLSTATION text,ENTOLLLANE text,ENTOLLSTATIONID text,ENTOLLLANEID text,ENTOLLSTATIONHEX text,ENTOLLLANEHEX text,ENVLP text,ENVLPC bigint,ENTIME text,ENWEIGHT bigint,ENAXLECOUNT bigint,EXTOLLSTATIONNAME text,EXTOLLSTATION text,EXTOLLLANE text,EXTOLLSTATIONID text,EXTOLLLANEID text,EXTOLLSTATIONHEX text,EXTOLLLANEHEX text,EXWEIGHT bigint,AXLECOUNT bigint,CARDCOSTFEE bigint,UNPAYFEE bigint,UNPAYFLAG bigint,UNPAYCARDCOST bigint,TICKETFEE bigint,UNIFIEDFEE bigint,TRANSCODE text,SHIFT bigint,OPERID bigint,OPERNAME text,LANEAPPVER text,ELECTRICALPERCENTAGE bigint,PARAVER text,CHECKSIGN bigint,LANETYPE bigint,OPEN bigint,BL_SUBCENTER bigint,BL_CENTER bigint,LDATE text,BATCHNUM text,PAKAGEID text,PAKAGETIME text,CHARGEBATCH text,PAYCARDTRANSN bigint,PAYORDERNUM text,PAYCODE text,VERIFYPASSTIME text,PASSID text,VEHICLESIGN text,PAYFEE bigint,FEE bigint,DISCOUNTFEE bigint,NOCARDCOUNT bigint,FEEMILEAGE bigint,SHORTFEE bigint,SHORTFEEMILEAGE bigint,FEERATE bigint,SPCRATEVERSION bigint,CARDTOTALAMOUNT bigint,OBUTOTALAMOUNT text,OBUTOTALDISCOUNTAMOUNT text,OBUPROVINCEFEE bigint,TOTALCOUNT bigint,PROVTRANSCOUNT bigint,PROVINCECOUNT bigint,DISCOUNTTYPE text,PROVINCEDISCOUNTFEE text,ORIGINFEE text,PAYTYPE bigint,ROADTYPE bigint,SIGNSTATUS bigint,GENTIME text,STATIONRECEIVETIME text,RECEIVETIME text,STATUS bigint,RESPONSECODE bigint,RESPONSEINFO text,NATIONRECEIVETIME text,PROTIME text,BATCHFILENAME text,SPLITFLAG bigint,PROSPLITTIME text,PROSPLITTYPE bigint,SPLITOWNERCOUNT bigint,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITREMARK text,CLEARDATE text,CLEARFLAG bigint,CLEARREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,CHARGERESULT bigint,ISSUECHARGETIME text,FIRSTCLEARFLAG bigint,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,ORIGINALFLAG bigint,SPLITBASE bigint,SPLITREMARK1 text,VACCINEFLAG bigint,APPOINTID text);

CREATE TABLE EXDFOREIGNGASTRANSACTION(ID text,GASSTATIONOPERATORID bigint,ISSUERID bigint,MESSAGEID bigint,FUELTYPE bigint,FUELPRICE bigint,FUELVOLUME bigint,TRANSDATE text,TRANSTIME text,FEE bigint,TERMINALTRANSNO text,OBUID text,VEHICLEID text,TAC text,TRANSNO bigint,TRANSTYPE text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,SERVICETYPE bigint,ALGORITHMIDENTIFIER bigint,REMARK text,RECEIVETIME text,GENTIME text,REMARKS text,ISSUECHARGETIME text,CHARGERESULT bigint,DISPUTEDRESULT text,DISPUTEDID text,DISPUTEDPROTIME text,CLEARDATE text,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,NATIONCLEARDATE text,CLEARBATCH text,STATUS bigint,ORGCODE bigint,CLEARBATCHSTATUS bigint,SPLITFLAG bigint,NATIONCLEARFLAG bigint,NATIONCLEARTIME text,NAME text,CLEARREMARK text,MANAGEID text);
CREATE TABLE EXDFOREIGNMUNICIPALTRANSACTION(ID text,MESSAGEID bigint,ISSUERID bigint,ORGCODE bigint,NAME text,TRANSDATE text,TRANSTIME text,`DESCRIBE` text,FEE bigint,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,VEHICLEID text,TAC text,TRANSNO bigint,TRANSTYPE text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,SERVICETYPE bigint,ALGORITHMIDENTIFIER bigint,REMARK text,RECEIVETIME text,GENTIME text,STATUS bigint,REMARKS text,ISSUECHARGETIME text,CHARGERESULT bigint,DISPUTEDRESULT text,DISPUTEDID text,DISPUTEDPROTIME text,NATIONCLEARDATE text,NATIONCLEARFLAG bigint,NATIONCLEARTIME text,CLEARDATE text,CLEARBATCHSTATUS bigint,CLEARBATCH text,CLEARREMARK text,SERVICEAREAOPERATORID bigint,MUNICIPALID bigint,TERMINALTRANSNO text,MANAGEID text,VEHICLETYPE bigint);
CREATE TABLE EXDFOREIGNPARKTRANSACTION(ID text,PARKOPERATORID bigint,ISSUERID bigint,MESSAGEID bigint,TRANSDATE text,TRANSTIME text,PARKTIME bigint,FEE bigint,TERMINALTRANSNO text,OBUID text,VEHICLEID text,VEHICLETYPE bigint,TAC text,TRANSNO bigint,TRANSTYPE text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,SERVICETYPE bigint,ALGORITHMIDENTIFIER bigint,REMARK text,RECEIVETIME text,GENTIME text,REMARKS text,ISSUECHARGETIME text,CHARGERESULT bigint,DISPUTEDRESULT text,DISPUTEDID text,DISPUTEDPROTIME text,NATIONCLEARDATE text,CLEARDATE text,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,CLEARBATCH text,STATUS bigint,ORGCODE bigint,CLEARBATCHSTATUS bigint,SPLITFLAG bigint,NATIONCLEARFLAG bigint,NATIONCLEARTIME text,CLEARREMARK text,NAME text,MANAGEID text);
CREATE TABLE EXDLOCALTRANSACTION(ID text,WASTETYPE bigint,OPERATORID bigint,ISSUERID bigint,TRANSDATE text,TRANSTIME text,PARKTIME bigint,FUELTYPE text,FUELPRICE text,FUELVOLUME text,`DESCRIBE` text,FEE bigint,ETCCARDTYPE bigint,ETCCARDNET bigint,ETCCARDID bigint,TERMINALTRANSNO text,OBUID bigint,VEHICLEID text,VEHICLETYPE bigint,TAC text,TRANSNO bigint,TRANSTYPE text,TERMINALNO text,PREBALANCE bigint,POSTBALANCE bigint,SERVICETYPE bigint,ALGORITHMIDENTIFIER bigint,REMARK text,RECEIVETIME text,GENTIME text,REMARKS text,ISSUECHARGETIME text,CHARGERESULT bigint,DISPUTEDRESULT text,DISPUTEDPROTIME text,CLEARBATCHSTATUS bigint,CLEARBATCH text,CLEARDATE text,BATCHNUM text,CHARGEBATCH text,ORGCODE bigint,SPLITFLAG bigint,CLEARREMARK text,NAME text,MANAGEID text,MODIFYFLAG bigint);

CREATE TABLE GANTRYCPCTRANSACTION(ALGORITHMIDENTIFIER bigint,CARDTOTALAMOUNT bigint,DESCRIPTION text,DIRECTION bigint,DISCOUNTFEE text,DISCOUNTTYPE text,ELECTRICALPERCENTAGE text,ENAXLECOUNT text,ENTIME text,ENTOLLLANEID text,ENWEIGHT text,ETCCARDID bigint,ETCCARDNET bigint,ETCCARDTYPE bigint,FEE text,FEECALCSPECIALS bigint,GANTRYHEX text,GANTRYKEY text,GANTRYTYPE bigint,GANTRYVERIFY bigint,GENTIME text,ID text,IDENTIFYVEHICLETYPE text,IDENTIFYVLP text,IDENTIFYVLPC text,INSPLITFLAG bigint,KEYVERSION bigint,NOCARDCOUNT bigint,OBUID bigint,OBUMAC text,OBUPROVINCEFEE bigint,OBUSIGN text,OBUSN bigint,OBUTOTALAMOUNT bigint,ORIGINALFLAG bigint,ORIGINFEE text,PASSID text,PAYFEE text,PROVINCECOUNT bigint,PROVINCEDISCOUNTFEE text,PROVTRANSCOUNT bigint,RATEVERSION text,REMARKS text,SECTIONID text,SECTIONNAME text,SPARE1 text,SPARE2 text,SPARE3 text,STATUS bigint,SUMTIME text,TAC text,TERMINALNO text,TERMINALTRANSNO text,TOLLGANTRYID text,TOLLINTERVALDISCOUNTFEE bigint,TOLLINTERVALFEE bigint,TOLLINTERVALID text,TOLLINTERVALNAME text,TOLLINTERVALPAYFEE bigint,TOLLINTERVALSIGN bigint,TOTALCOUNT bigint,TRANSFEE bigint,TRANSNUM bigint,TRANSTIME text,TRANSTYPE text,TYPE bigint,UNIONPAYFLAG bigint,UNIONPAYTYPEGROUP text,VEHICLECLASS bigint,VEHICLESEAT text,VEHICLESIGN text,VEHICLESIGNID text,VEHICLETYPE text,VLP text,VLPC bigint);
CREATE TABLE GANTRYETCTRANSACTION(ALGORITHMIDENTIFIER bigint,CARDTOTALAMOUNT bigint,DESCRIPTION text,DIRECTION bigint,DISCOUNTFEE text,DISCOUNTTYPE text,ELECTRICALPERCENTAGE text,ENAXLECOUNT text,ENTIME text,ENTOLLLANEID text,ENWEIGHT text,ETCCARDID bigint,ETCCARDNET bigint,ETCCARDTYPE bigint,FEE text,FEECALCSPECIALS text,GANTRYHEX text,GANTRYKEY text,GANTRYTYPE bigint,GANTRYVERIFY bigint,GENTIME text,ID text,IDENTIFYVEHICLETYPE text,IDENTIFYVLP text,IDENTIFYVLPC text,INSPLITFLAG bigint,KEYVERSION bigint,NOCARDCOUNT bigint,OBUID bigint,OBUMAC text,OBUPROVINCEFEE bigint,OBUSIGN text,OBUSN bigint,OBUTOTALAMOUNT bigint,ORIGINALFLAG bigint,ORIGINFEE text,PASSID text,PAYFEE text,PROVINCECOUNT bigint,PROVINCEDISCOUNTFEE text,PROVTRANSCOUNT bigint,RATEVERSION text,REMARKS text,SECTIONID text,SECTIONNAME text,SPARE1 text,SPARE2 text,SPARE3 text,STATUS bigint,SUMTIME text,TAC text,TERMINALNO text,TERMINALTRANSNO text,TOLLGANTRYID text,TOLLINTERVALDISCOUNTFEE bigint,TOLLINTERVALFEE bigint,TOLLINTERVALID text,TOLLINTERVALNAME text,TOLLINTERVALPAYFEE bigint,TOLLINTERVALSIGN bigint,TOTALCOUNT bigint,TRANSFEE bigint,TRANSNUM bigint,TRANSTIME text,TRANSTYPE text,TYPE bigint,UNIONPAYFLAG bigint,UNIONPAYTYPEGROUP text,VEHICLECLASS bigint,VEHICLESEAT text,VEHICLESIGN text,VEHICLESIGNID text,VEHICLETYPE text,VLP text,VLPC bigint);

CREATE TABLE TOLLCHANGETRANSACTIONS(ID text,MODIFYFLAG bigint,CROSSFLAG bigint,TOLLPROVINCEID bigint,ISSUERID bigint,PAYERID bigint,RECEIVEID bigint,RECEIVETYPE bigint,TRANSACTIONID text,ORDERID text,EXTIME text,LDATE text,CARD text,VLPC bigint,VLP text,PAYTYPE bigint,ORIGINALFEE bigint,FEE bigint,STATUS bigint,PROTIME text,GENTIME text,SPLITOWNERCOUNT bigint,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP bigint,SPLITOWNERPAYFEEGROUP bigint,SPLITOWNERDISFEEGROUP bigint,DESCRIPTION text,EXTOLLSTATION text,EXTOLLSTATIONNAME text,EXTOLLSTATIONID text,PAYORDERNUM text,PAYCODE text,ROADTYPE bigint,BATCHNUM text,NATIONCLEARDATE text,CLEARBATCHSTATUS bigint,CHARGEBATCH text,CLEARBATCH text,CLEARDATE text,ETCCARDNET bigint,VERIFYPASSTIME text,ETCCARDID bigint,SPLITFLAG bigint,SPLITTIME text,ISSUECHARGETIME text,DISPUTEDPROTIME text,CHARGERESULT bigint,EXTOLLLANEID text,ETCCARDTYPE bigint,EXVEHICLETYPE bigint,PASSID float,WASTETYPE bigint,REMARKS text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,CLEARREMARK text,SPLITCOUNT text,SPLITGROUP text,SPLITFEEGROUP text,RETURNFLAG bigint,ORIGINALFLAG bigint);

CREATE TABLE ETCSPLITRESULTEXIT(ID text,RESULTTYPE text,MEDIATYPE text,MEDIANO text,TRANSPAYTYPE text,SPLITTIME text,EXITFEETYPE text,TOLLPROVINCEID text,SERPROVINCEID text,ISSUERID text,CARDID text,ETCCARDTYPE text,EXTIME text,EXVLP text,EXVLPC text,IDENTIFYVLP text,IDENTIFYVLPC text,VEHICLETYPE text,VEHICLECLASS text,TAC text,DESCRIPTION text,SPECIALTYPE text,ENTOLLLANEID text,ENTOLLLANEHEX text,ENTIME text,ENTOLLSTATIONNAME text,EXTOLLSTATIONNAME text,EXTOLLLANEID text,PASSID text,FEE text,ACCOUNTSTATUS text,ACCOUNTTIME text,RECEIVETIME text,RESULT text,PROCESSTIME text,VEHICLESIGN text,VERSION text,STORAGETIME text,SPLITCHECKSTATUS text,SPLITCHECKTIME text,SPLITCHECKDATE text,SPARE1 text,SPARE2 text,SPARE3 text,SPLITINFEE text,SPLITCROSSFEE text,NATIONSPLITTIME text,NATIONSPLITTYPE text,SPLITFLAG text,PROSPLITTIME text,PROSPLITTYPE text,SPLITOWNERCOUNT text,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITCHECKFLAG text,SPLITCNT text,SPLITFEE text,REALSPLITCNT text,SPLITREMARK text,CLEARDATE text,CLEARFLAG text,CLEARREMARK text,FIRSTCLEARFLAG text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,SPLITREMARK1 text,SPLITBASE text,APPOINTID text);
CREATE TABLE ETCSPLITRESULTGANTRY(TOLLPROVINCEID text,ISSUERID text,ID text,REQUESTERID text,REQUESTERTYPE text,EXITTRANSTYPE text,PROCESSTIME text,SPLITTIME text,PASSID text,EXTRANSACTIONID text,EXTIME text,EXTOLLSTATIONNAME text,EXTOLLLANEID text,EXFEE text,FEE text,SPLITFEE text,SPLITRULE text,EXITFEETYPE text,VEHICLESIGN text,TOLLINTERVALID text,TOLLINTERVALSPLITFEE text,TRANSNUM text,VERSION text,STORAGETIME text,SPLITCHECKDATE text,SPLITCHECKSTATUS text,SPLITCHECKTIME text,SPLITFLAG text,PROSPLITTIME text,PROSPLITTYPE text,SPLITOWNERCOUNT text,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITREMARK text,CLEARDATE text,CLEARFLAG text,CLEARREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,VEHICLETYPE text,SERPROVINCEID text,TOLLINTERVALID1 text,TOLLINTERVALID2 text,FIRSTCLEARFLAG text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,SPLITBASE text,SPLITREMARK1 text,VLP text,VLPC text,CARDID text,ETCCARDTYPE text,MEDIATYPE text,MEDIANO text,ENTOLLLANEID text,APPOINTID text);
CREATE TABLE OTHERSPLITRESULTGANTRY(TOLLPROVINCEID text,ID text,SPLITTIME text,PASSID text,EXTRANSACTIONID text,EXTIME text,EXTOLLSTATIONNAME text,EXTOLLLANEID text,EXFEE text,FEE text,SPLITFEE text,SPLITRULE text,EXITFEETYPE text,VEHICLESIGN text,TOLLINTERVALID text,TOLLINTERVALSPLITFEE text,TRANSNUM text,VERSION text,STORAGETIME text,SPLITCHECKSTATUS text,SPLITCHECKTIME text,SPLITCHECKDATE text,SPLITFLAG text,PROSPLITTIME text,PROSPLITTYPE text,SPLITOWNERCOUNT text,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITREMARK text,CLEARDATE text,CLEARFLAG text,CLEARREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,VEHICLETYPE text,FIRSTCLEARFLAG text,TOLLINTERVALID1 text,TOLLINTERVALID2 text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,SPLITBASE text,SPLITREMARK1 text,VLP text,VLPC text,CARDID text,ETCCARDTYPE text,MEDIATYPE text,MEDIANO text,ENTOLLLANEID text,APPOINTID text);
CREATE TABLE OTHERSPLITRESULTEXIT(ID text,TOLLPROVINCEID text,MEDIATYPE text,MEDIANO text,EXITFEETYPE text,OBUSIGN text,IDENTIFICATION text,CARDID text,ETCCARDTYPE text,EXTIME text,EXVLP text,EXVLPC text,IDENTIFYVLP text,IDENTIFYVLPC text,VEHICLESIGNID text,VEHICLETYPE text,VEHICLECLASS text,EXWEIGHT text,AXLECOUNT text,DESCRIPTION text,SPECIALTYPE text,ENTOLLLANEID text,ENTOLLLANEHEX text,ENTIME text,ENWEIGHT text,ENAXLECOUNT text,ENTOLLSTATIONNAME text,EXTOLLSTATIONNAME text,EXTOLLLANEID text,SPLITTIME text,PASSID text,VEHICLESIGN text,FEE text,NOCARDCOUNT text,FEEMILEAGE text,SHORTFEE text,SHORTFEEMILEAGE text,FEERATE text,SPCRATEVERSION text,CARDTOTALAMOUNT text,OBUTOTALAMOUNT text,OBUTOTALDISCOUNTAMOUNT text,OBUPROVINCEFEE text,TOTALCOUNT text,PROVTRANSCOUNT text,PROVINCECOUNT text,DISCOUNTTYPE text,PROVINCEDISCOUNTFEE text,ORIGINFEE text,VERSION text,STORAGETIME text,SPLITCHECKDATE text,SPLITCHECKSTATUS text,SPLITCHECKTIME text,SPLITINFEE text,SPLITCROSSFEE text,NATIONSPLITTIME text,NATIONSPLITTYPE text,SPLITFLAG text,PROSPLITTIME text,PROSPLITTYPE text,SPLITOWNERCOUNT text,SPLITOWNERGROUP text,SPLITOWNERFEEGROUP text,SPLITOWNERPAYFEEGROUP text,SPLITOWNERDISFEEGROUP text,SPLITCHECKFLAG text,SPLITCNT text,SPLITFEE text,REALSPLITCNT text,SPLITREMARK text,SPARE1 text,SPARE2 text,SPARE3 text,CLEARDATE text,CLEARFLAG text,CLEARREMARK text,FIRSTCLEARFLAG text,SPLITOWNERGROUP1 text,SPLITOWNERGROUP2 text,SPLITREMARK1 text,SPLITBASE text,REVERSEFLAG text,APPOINTID text);

CREATE TABLE ETCCLEARRESULT(CROPID text,TOLLSECTIONID text,TOLLINTERVALID text,TOLLSTATION text,VEHICLETYPE text,PAYTYPE text,PAYCARDTYPE text,PROVINCETYPE text,LDATE text,NATIONCLEARDATE text,NATIONSPLITDATE text,CLEARDATE text,MULTIPROVINCE text,MODIFYFLAG text,ROADTYPE text,BUSIBATCH text,UNIONPAYTYPE text,CLEARTYPE text,ISSUERID text,TOLLPROVINCEID text,SERPROVINCEID text,TOLLCROPID text,SECTIONID text,AMOUNT bigint,DISCOUNTAMOUNT bigint,CHARGEAMOUNT bigint,REPORTFLAG text,,EXTOLLSTATION text);
CREATE TABLE CASHCLEARRESULT(CROPID text,TOLLSECTIONID text,TOLLINTERVALID text,TOLLSTATION text,VEHICLETYPE text,PAYTYPE text,PAYCARDTYPE text,PROVINCETYPE text,LDATE text,NATIONCLEARDATE text,NATIONSPLITDATE text,CLEARDATE text,MULTIPROVINCE text,MODIFYFLAG text,ROADTYPE text,BUSIBATCH text,UNIONPAYTYPE text,CLEARTYPE text,ISSUERID text,TOLLPROVINCEID text,SERPROVINCEID text,TOLLCROPID text,SECTIONID text,AMOUNT bigint,DISCOUNTAMOUNT bigint,CHARGEAMOUNT bigint,REPORTFLAG text,,REPORTDATE text,EXTOLLSTATION text);
CREATE TABLE EXPANDCLEARRESULT(CROPID text,VEHICLETYPE text,PAYCARDTYPE text,LDATE bigint,NATIONCLEARDATE text,CLEARDATE text,BUSIBATCH text,CLEARTYPE text,ISSUERID text,AMOUNT bigint,REPORTFLAG text,,MODIFYFLAG text);
