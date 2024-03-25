package info.nemoworks.highlink.model.gantryTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @description: 对应预处理输入中门架接收的 “ETC门架计费扣费交易数据接收表（tbl_GantryWasteRec）”
 * @author：jimi
 * @date: 2024/1/21
 * @Copyright：
 */

@Data
public class GantryRawTransaction implements HighwayTransaction, PathTransaction {
    @JsonProperty(value = "TRADEID", access = JsonProperty.Access.WRITE_ONLY)
    private String iD;
    @JsonProperty(value = "GANTRYID", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYID;
    @JsonProperty(value = "ORIGINALFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int oRIGINALFLAG;
    @JsonProperty(value = "COMPUTERORDER", access = JsonProperty.Access.WRITE_ONLY)
    private int cOMPUTERORDER;
    @JsonProperty(value = "HOURBATCHNO", access = JsonProperty.Access.WRITE_ONLY)
    private String hOURBATCHNO;
    @JsonProperty(value = "GANTRYORDERNUM", access = JsonProperty.Access.WRITE_ONLY)
    private int gANTRYORDERNUM;
    @JsonProperty(value = "GANTRYHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYHEX;
    @JsonProperty(value = "GANTRYHEXOPPOSITE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYHEXOPPOSITE;
    @JsonProperty(value = "TRANSTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSTIME;
    @JsonProperty(value = "PAYFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYFEE;
    @JsonProperty(value = "FEE", access = JsonProperty.Access.WRITE_ONLY)
    private String fEE;
    @JsonProperty(value = "DISCOUNTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String dISCOUNTFEE;
    @JsonProperty(value = "TRANSFEE", access = JsonProperty.Access.WRITE_ONLY)
    private int tRANSFEE;
    @JsonProperty(value = "MEDIATYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int mEDIATYPE;
    @JsonProperty(value = "OBUSIGN", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUSIGN;
    @JsonProperty(value = "TOLLINTERVALID", access = JsonProperty.Access.WRITE_ONLY)
    private String tOLLINTERVALID;
    @JsonProperty(value = "PAYFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String pAYFEEGROUP;
    @JsonProperty(value = "FEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEGROUP;
    @JsonProperty(value = "DISCOUNTFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String dISCOUNTFEEGROUP;
    @JsonProperty(value = "ENWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private String eNWEIGHT;
    @JsonProperty(value = "ENAXLECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String eNAXLECOUNT;
    @JsonProperty(value = "VLP", access = JsonProperty.Access.WRITE_ONLY)
    private String vLP;
    @JsonProperty(value = "VLPC", access = JsonProperty.Access.WRITE_ONLY)
    private int vLPC;
    @JsonProperty(value = "VEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLETYPE;
    @JsonProperty(value = "IDENTIFYVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String iDENTIFYVEHICLETYPE;
    @JsonProperty(value = "VEHICLECLASS", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLECLASS;
    @JsonProperty(value = "TAC", access = JsonProperty.Access.WRITE_ONLY)
    private String tAC;
    @JsonProperty(value = "TRANSTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSTYPE;
    @JsonProperty(value = "TERMINALNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tERMINALNO;
    @JsonProperty(value = "TERMINALTRANSNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tERMINALTRANSNO;
    @JsonProperty(value = "TRANSNO", access = JsonProperty.Access.WRITE_ONLY)
    private String tRANSNO;
    @JsonProperty(value = "SERVICETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int sERVICETYPE;
    @JsonProperty(value = "ALGORITHMIDENTIFIER", access = JsonProperty.Access.WRITE_ONLY)
    private int aLGORITHMIDENTIFIER;
    @JsonProperty(value = "KEYVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int kEYVERSION;
    @JsonProperty(value = "ANTENNAID", access = JsonProperty.Access.WRITE_ONLY)
    private int aNTENNAID;
    @JsonProperty(value = "TOLLMODEVER", access = JsonProperty.Access.WRITE_ONLY)
    private long tOLLMODEVER;
    @JsonProperty(value = "TOLLPARAVER", access = JsonProperty.Access.WRITE_ONLY)
    private long tOLLPARAVER;
    @JsonProperty(value = "CONSUMETIME", access = JsonProperty.Access.WRITE_ONLY)
    private int cONSUMETIME;
    @JsonProperty(value = "PASSSTATE", access = JsonProperty.Access.WRITE_ONLY)
    private int pASSSTATE;
    @JsonProperty(value = "ENTOLLLANEID", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLLANEID;
    @JsonProperty(value = "ENTOLLSTATIONHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTOLLSTATIONHEX;
    @JsonProperty(value = "ENTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String eNTIME;
    @JsonProperty(value = "ENLANETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int eNLANETYPE;
    @JsonProperty(value = "PASSID", access = JsonProperty.Access.WRITE_ONLY)
    private String pASSID;
    @JsonProperty(value = "LASTGANTRYHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYHEX;
    @JsonProperty(value = "LASTGANTRYTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYTIME;
    @JsonProperty(value = "OBUMAC", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUMAC;
    @JsonProperty(value = "OBUISSUEID", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUISSUEID;
    @JsonProperty(value = "OBUSN", access = JsonProperty.Access.WRITE_ONLY)
    private long oBUSN;
    @JsonProperty(value = "OBUVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUVERSION;
    @JsonProperty(value = "OBUSTARTDATE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUSTARTDATE;
    @JsonProperty(value = "OBUENDDATE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUENDDATE;
    @JsonProperty(value = "OBUELECTRICAL", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUELECTRICAL;
    @JsonProperty(value = "OBUSTATE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUSTATE;
    @JsonProperty(value = "OBUVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUVLP;
    @JsonProperty(value = "OBUVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUVLPC;
    @JsonProperty(value = "OBUVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUVEHICLETYPE;
    @JsonProperty(value = "VEHICLEUSERTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLEUSERTYPE;
    @JsonProperty(value = "VEHICLESEAT", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESEAT;
    @JsonProperty(value = "AXLECOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private int aXLECOUNT;
    @JsonProperty(value = "TOTALWEIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int tOTALWEIGHT;
    @JsonProperty(value = "VEHICLELENGTH", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLELENGTH;
    @JsonProperty(value = "VEHICLEWIDTH", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLEWIDTH;
    @JsonProperty(value = "VEHICLEHIGHT", access = JsonProperty.Access.WRITE_ONLY)
    private int vEHICLEHIGHT;
    @JsonProperty(value = "CPUNETID", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUNETID;
    @JsonProperty(value = "CPUISSUEID", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUISSUEID;
    @JsonProperty(value = "CPUVLP", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUVLP;
    @JsonProperty(value = "CPUVLPC", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUVLPC;
    @JsonProperty(value = "CPUVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUVEHICLETYPE;
    @JsonProperty(value = "CPUSTARTDATE", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUSTARTDATE;
    @JsonProperty(value = "CPUENDDATE", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUENDDATE;
    @JsonProperty(value = "CPUVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUVERSION;
    @JsonProperty(value = "CPUCARDTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUCARDTYPE;
    @JsonProperty(value = "CPUCARDID", access = JsonProperty.Access.WRITE_ONLY)
    private String cPUCARDID;
    @JsonProperty(value = "BALANCEBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String bALANCEBEFORE;
    @JsonProperty(value = "BALANCEAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private String bALANCEAFTER;
    @JsonProperty(value = "GANTRYPASSCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYPASSCOUNT;
    @JsonProperty(value = "GANTRYPASSINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYPASSINFO;
    @JsonProperty(value = "FEEPROVINFO", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVINFO;
    @JsonProperty(value = "FEESUMLOCALBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String fEESUMLOCALBEFORE;
    @JsonProperty(value = "FEESUMLOCALAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private String fEESUMLOCALAFTER;
    @JsonProperty(value = "FEECALCRESULT", access = JsonProperty.Access.WRITE_ONLY)
    private int fEECALCRESULT;
    @JsonProperty(value = "FEEINFO1", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEINFO1;
    @JsonProperty(value = "FEEINFO2", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEINFO2;
    @JsonProperty(value = "FEEINFO3", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEINFO3;
    @JsonProperty(value = "HOLIDAYSTATE", access = JsonProperty.Access.WRITE_ONLY)
    private String hOLIDAYSTATE;
    @JsonProperty(value = "TRADERESULT", access = JsonProperty.Access.WRITE_ONLY)
    private String tRADERESULT;
    @JsonProperty(value = "SPECIALTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String sPECIALTYPE;
    @JsonProperty(value = "VERIFYCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYCODE;
    @JsonProperty(value = "INTERRUPTSIGNAL", access = JsonProperty.Access.WRITE_ONLY)
    private String iNTERRUPTSIGNAL;
    @JsonProperty(value = "VEHICLEPICID", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLEPICID;
    @JsonProperty(value = "VEHICLETAILPICID", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLETAILPICID;
    @JsonProperty(value = "MATCHSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private String mATCHSTATUS;
    @JsonProperty(value = "VALIDSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private String vALIDSTATUS;
    @JsonProperty(value = "DEALSTATUS", access = JsonProperty.Access.WRITE_ONLY)
    private String dEALSTATUS;
    @JsonProperty(value = "RELATEDTRADEID", access = JsonProperty.Access.WRITE_ONLY)
    private String rELATEDTRADEID;
    @JsonProperty(value = "ALLRELATEDTRADEID", access = JsonProperty.Access.WRITE_ONLY)
    private String aLLRELATEDTRADEID;
    @JsonProperty(value = "STATIONDBTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONDBTIME;
    @JsonProperty(value = "STATIONDEALTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONDEALTIME;
    @JsonProperty(value = "STATIONVALIDTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONVALIDTIME;
    @JsonProperty(value = "STATIONMATCHTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String sTATIONMATCHTIME;
    @JsonProperty(value = "DESCRIPTION", access = JsonProperty.Access.WRITE_ONLY)
    private String dESCRIPTION;
    @JsonProperty(value = "REQUESTNAME", access = JsonProperty.Access.WRITE_ONLY)
    private String rEQUESTNAME;
    @JsonProperty(value = "RESPONSENAME", access = JsonProperty.Access.WRITE_ONLY)
    private String rESPONSENAME;
    @JsonProperty(value = "RECEIVETIME", access = JsonProperty.Access.WRITE_ONLY)
    private String rECEIVETIME;
    @JsonProperty(value = "MSGID", access = JsonProperty.Access.WRITE_ONLY)
    private String mSGID;
    @JsonProperty(value = "SPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE1;
    @JsonProperty(value = "SPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE2;
    @JsonProperty(value = "SPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE3;
    @JsonProperty(value = "SPARE4", access = JsonProperty.Access.WRITE_ONLY)
    private String sPARE4;
    @JsonProperty(value = "RECORDGENTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String rECORDGENTIME;
    @JsonProperty(value = "VERIFYPASSTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String vERIFYPASSTIME;
    @JsonProperty(value = "REMARKS", access = JsonProperty.Access.WRITE_ONLY)
    private String rEMARKS;
    @JsonProperty(value = "VEHICLESIGN", access = JsonProperty.Access.WRITE_ONLY)
    private String vEHICLESIGN;
    @JsonProperty(value = "LASTGANTRYHEXFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYHEXFEE;
    @JsonProperty(value = "LASTGANTRYHEXPASS", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYHEXPASS;
    @JsonProperty(value = "FEECALCSPECIAL", access = JsonProperty.Access.WRITE_ONLY)
    private String fEECALCSPECIAL;
    @JsonProperty(value = "CHARGESSPECIALTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String cHARGESSPECIALTYPE;
    @JsonProperty(value = "ISFIXDATA", access = JsonProperty.Access.WRITE_ONLY)
    private String iSFIXDATA;
    @JsonProperty(value = "GANTRYTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int gANTRYTYPE;
    @JsonProperty(value = "OBUPROVFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUPROVFEESUMBEFORE;
    @JsonProperty(value = "OBUPROVFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPROVFEESUMAFTER;
    @JsonProperty(value = "CARDFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDFEESUMBEFORE;
    @JsonProperty(value = "CARDFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int cARDFEESUMAFTER;
    @JsonProperty(value = "NOCARDTIMESBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int nOCARDTIMESBEFORE;
    @JsonProperty(value = "NOCARDTIMESAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int nOCARDTIMESAFTER;
    @JsonProperty(value = "PROVINCENUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVINCENUMBEFORE;
    @JsonProperty(value = "PROVINCENUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int pROVINCENUMAFTER;
    @JsonProperty(value = "OBUTOTALTRADESUCCNUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUTOTALTRADESUCCNUMBEFORE;
    @JsonProperty(value = "OBUTOTALTRADESUCCNUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUTOTALTRADESUCCNUMAFTER;
    @JsonProperty(value = "OBUPROVTRADESUCCNUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPROVTRADESUCCNUMBEFORE;
    @JsonProperty(value = "OBUPROVTRADESUCCNUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPROVTRADESUCCNUMAFTER;
    @JsonProperty(value = "OBUTRADERESULT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUTRADERESULT;
    @JsonProperty(value = "TRADETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int tRADETYPE;
    @JsonProperty(value = "OBUINFOTYPEREAD", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUINFOTYPEREAD;
    @JsonProperty(value = "OBUINFOTYPEWRITE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUINFOTYPEWRITE;
    @JsonProperty(value = "OBUPASSSTATE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPASSSTATE;
    @JsonProperty(value = "FEEVEHICLETYPE", access = JsonProperty.Access.WRITE_ONLY)
    private int fEEVEHICLETYPE;
    @JsonProperty(value = "OBULASTGANTRYHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String oBULASTGANTRYHEX;
    @JsonProperty(value = "OBULASTGANTRYTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String oBULASTGANTRYTIME;
    @JsonProperty(value = "RATECOMPUTE", access = JsonProperty.Access.WRITE_ONLY)
    private int rATECOMPUTE;
    @JsonProperty(value = "RATEFITCOUNT", access = JsonProperty.Access.WRITE_ONLY)
    private String rATEFITCOUNT;
    @JsonProperty(value = "OBUPAYFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUPAYFEESUMBEFORE;
    @JsonProperty(value = "OBUPAYFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPAYFEESUMAFTER;
    @JsonProperty(value = "OBUDISCOUNTFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUDISCOUNTFEESUMBEFORE;
    @JsonProperty(value = "OBUDISCOUNTFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUDISCOUNTFEESUMAFTER;
    @JsonProperty(value = "FEEMILEAGE", access = JsonProperty.Access.WRITE_ONLY)
    private int fEEMILEAGE;
    @JsonProperty(value = "OBUMILEAGEBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUMILEAGEBEFORE;
    @JsonProperty(value = "OBUMILEAGEAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUMILEAGEAFTER;
    @JsonProperty(value = "PROVMINFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String pROVMINFEE;
    @JsonProperty(value = "FEESPARE1", access = JsonProperty.Access.WRITE_ONLY)
    private int fEESPARE1;
    @JsonProperty(value = "FEESPARE2", access = JsonProperty.Access.WRITE_ONLY)
    private int fEESPARE2;
    @JsonProperty(value = "FEESPARE3BAK", access = JsonProperty.Access.WRITE_ONLY)
    private String fEESPARE3BAK;
    @JsonProperty(value = "FEEPROVBEGINHEX", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVBEGINHEX;
    @JsonProperty(value = "TRADEREADCIPHERTEXT", access = JsonProperty.Access.WRITE_ONLY)
    private String tRADEREADCIPHERTEXT;
    @JsonProperty(value = "READCIPHERTEXTVERIFY", access = JsonProperty.Access.WRITE_ONLY)
    private String rEADCIPHERTEXTVERIFY;
    @JsonProperty(value = "TRADEWRITECIPHERTEXT", access = JsonProperty.Access.WRITE_ONLY)
    private String tRADEWRITECIPHERTEXT;
    @JsonProperty(value = "OBUFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUFEESUMBEFORE;
    @JsonProperty(value = "OBUFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUFEESUMAFTER;
    @JsonProperty(value = "OBUPROVPAYFEESUMBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUPROVPAYFEESUMBEFORE;
    @JsonProperty(value = "OBUPROVPAYFEESUMAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private int oBUPROVPAYFEESUMAFTER;
    @JsonProperty(value = "PATHFITFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int pATHFITFLAG;
    @JsonProperty(value = "FEECALCSPECIALS", access = JsonProperty.Access.WRITE_ONLY)
    private int fEECALCSPECIALS;
    @JsonProperty(value = "PAYFEEPROVSUMLOCAL", access = JsonProperty.Access.WRITE_ONLY)
    private int pAYFEEPROVSUMLOCAL;
    @JsonProperty(value = "PCRSUVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private int pCRSUVERSION;
    @JsonProperty(value = "GANTRYPASSINFOAFTER", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYPASSINFOAFTER;
    @JsonProperty(value = "UPDATERESULT", access = JsonProperty.Access.WRITE_ONLY)
    private int uPDATERESULT;
    @JsonProperty(value = "CPCFEETRADERESULT", access = JsonProperty.Access.WRITE_ONLY)
    private int cPCFEETRADERESULT;
    @JsonProperty(value = "FEEPROVEF04", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVEF04;
    @JsonProperty(value = "FITPROVFLAG", access = JsonProperty.Access.WRITE_ONLY)
    private int fITPROVFLAG;
    @JsonProperty(value = "GANTRYPASSCOUNTBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private int gANTRYPASSCOUNTBEFORE;
    @JsonProperty(value = "FEEPROVBEGINHEXFIT", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVBEGINHEXFIT;
    @JsonProperty(value = "FEEPROVBEGINTIMEFIT", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVBEGINTIMEFIT;
    @JsonProperty(value = "TOLLINTERVALSIGN", access = JsonProperty.Access.WRITE_ONLY)
    private int tOLLINTERVALSIGN;
    @JsonProperty(value = "PROVMINFEECALCMODE", access = JsonProperty.Access.WRITE_ONLY)
    private String pROVMINFEECALCMODE;
    @JsonProperty(value = "FEEPROVBEGINTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVBEGINTIME;
    @JsonProperty(value = "FEESUMLOCALAFTEREF04", access = JsonProperty.Access.WRITE_ONLY)
    private String fEESUMLOCALAFTEREF04;
    @JsonProperty(value = "LASTGANTRYFEEPASS", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYFEEPASS;
    @JsonProperty(value = "LASTGANTRYMILEPASS", access = JsonProperty.Access.WRITE_ONLY)
    private String lASTGANTRYMILEPASS;
    @JsonProperty(value = "RSUMANUID", access = JsonProperty.Access.WRITE_ONLY)
    private String rSUMANUID;
    @JsonProperty(value = "FEEDATAVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEDATAVERSION;
    @JsonProperty(value = "GANTRYHEXOPPOTIME", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYHEXOPPOTIME;
    @JsonProperty(value = "OBUPAYFEESUMAFTERNOFIT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUPAYFEESUMAFTERNOFIT;
    @JsonProperty(value = "OBUFEESUMAFTERNOFIT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUFEESUMAFTERNOFIT;
    @JsonProperty(value = "OBUMILEAGEAFTERNOFIT", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUMILEAGEAFTERNOFIT;
    @JsonProperty(value = "OBUVEHICLEUSERTYPE", access = JsonProperty.Access.WRITE_ONLY)
    private String oBUVEHICLEUSERTYPE;
    @JsonProperty(value = "FEEPROVBEGINHEXBEFORE", access = JsonProperty.Access.WRITE_ONLY)
    private String fEEPROVBEGINHEXBEFORE;
    @JsonProperty(value = "GANTRYFITRESULT", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITRESULT;
    @JsonProperty(value = "GANTRYFITVERSION", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITVERSION;
    @JsonProperty(value = "GANTRYFITHEXS", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITHEXS;
    @JsonProperty(value = "GANTRYFITTOLLS", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITTOLLS;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVPAYFEE;
    @JsonProperty(value = "GANTRYFITNPROVDISCONTFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVDISCONTFEE;
    @JsonProperty(value = "GANTRYFITNPROVREALFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVREALFEE;
    @JsonProperty(value = "GANTRYFITNPROVMILES", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVMILES;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVPAYFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVDISCONTFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVDISCONTFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVREALFEEGROUP", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVREALFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEESUM", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVPAYFEESUM;
    @JsonProperty(value = "GANTRYFITNPROVREALFEESUM", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVREALFEESUM;
    @JsonProperty(value = "GANTRYFITNPROVMILESUM", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVMILESUM;
    @JsonProperty(value = "GANTRYFITNPROVPREREALFEE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNPROVPREREALFEE;
    @JsonProperty(value = "PATHFITDESC", access = JsonProperty.Access.WRITE_ONLY)
    private String pATHFITDESC;
    @JsonProperty(value = "GANTRYFITNEIGHBORPROVCODE", access = JsonProperty.Access.WRITE_ONLY)
    private String gANTRYFITNEIGHBORPROVCODE;
    @JsonProperty(value = "FEESPARE3", access = JsonProperty.Access.WRITE_ONLY)
    private String fEESPARE3;

    public boolean peekETC() {
        return this.getMEDIATYPE() != 1;
    }

    @Override
    public String peekTime() {
        return tRANSTIME;
    }
}
