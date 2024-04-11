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
    @JsonProperty(value = "TRADEID" )
    private String iD;
    @JsonProperty(value = "GANTRYID" )
    private String gANTRYID;
    @JsonProperty(value = "ORIGINALFLAG" )
    private int oRIGINALFLAG;
    @JsonProperty(value = "COMPUTERORDER" )
    private int cOMPUTERORDER;
    @JsonProperty(value = "HOURBATCHNO" )
    private String hOURBATCHNO;
    @JsonProperty(value = "GANTRYORDERNUM" )
    private int gANTRYORDERNUM;
    @JsonProperty(value = "GANTRYHEX" )
    private String gANTRYHEX;
    @JsonProperty(value = "GANTRYHEXOPPOSITE" )
    private String gANTRYHEXOPPOSITE;
    @JsonProperty(value = "TRANSTIME" )
    private String tRANSTIME;
    @JsonProperty(value = "PAYFEE" )
    private String pAYFEE;
    @JsonProperty(value = "FEE" )
    private String fEE;
    @JsonProperty(value = "DISCOUNTFEE" )
    private String dISCOUNTFEE;
    @JsonProperty(value = "TRANSFEE" )
    private int tRANSFEE;
    @JsonProperty(value = "MEDIATYPE" )
    private int mEDIATYPE;
    @JsonProperty(value = "OBUSIGN" )
    private String oBUSIGN;
    @JsonProperty(value = "TOLLINTERVALID" )
    private String tOLLINTERVALID;
    @JsonProperty(value = "PAYFEEGROUP" )
    private String pAYFEEGROUP;
    @JsonProperty(value = "FEEGROUP" )
    private String fEEGROUP;
    @JsonProperty(value = "DISCOUNTFEEGROUP" )
    private String dISCOUNTFEEGROUP;
    @JsonProperty(value = "ENWEIGHT" )
    private String eNWEIGHT;
    @JsonProperty(value = "ENAXLECOUNT" )
    private String eNAXLECOUNT;
    @JsonProperty(value = "VLP" )
    private String vLP;
    @JsonProperty(value = "VLPC" )
    private int vLPC;
    @JsonProperty(value = "VEHICLETYPE" )
    private String vEHICLETYPE;
    @JsonProperty(value = "IDENTIFYVEHICLETYPE" )
    private String iDENTIFYVEHICLETYPE;
    @JsonProperty(value = "VEHICLECLASS" )
    private int vEHICLECLASS;
    @JsonProperty(value = "TAC" )
    private String tAC;
    @JsonProperty(value = "TRANSTYPE" )
    private String tRANSTYPE;
    @JsonProperty(value = "TERMINALNO" )
    private String tERMINALNO;
    @JsonProperty(value = "TERMINALTRANSNO" )
    private String tERMINALTRANSNO;
    @JsonProperty(value = "TRANSNO" )
    private String tRANSNO;
    @JsonProperty(value = "SERVICETYPE" )
    private int sERVICETYPE;
    @JsonProperty(value = "ALGORITHMIDENTIFIER" )
    private int aLGORITHMIDENTIFIER;
    @JsonProperty(value = "KEYVERSION" )
    private int kEYVERSION;
    @JsonProperty(value = "ANTENNAID" )
    private int aNTENNAID;
    @JsonProperty(value = "TOLLMODEVER" )
    private long tOLLMODEVER;
    @JsonProperty(value = "TOLLPARAVER" )
    private long tOLLPARAVER;
    @JsonProperty(value = "CONSUMETIME" )
    private int cONSUMETIME;
    @JsonProperty(value = "PASSSTATE" )
    private int pASSSTATE;
    @JsonProperty(value = "ENTOLLLANEID" )
    private String eNTOLLLANEID;
    @JsonProperty(value = "ENTOLLSTATIONHEX" )
    private String eNTOLLSTATIONHEX;
    @JsonProperty(value = "ENTIME" )
    private String eNTIME;
    @JsonProperty(value = "ENLANETYPE" )
    private int eNLANETYPE;
    @JsonProperty(value = "PASSID" )
    private String pASSID;
    @JsonProperty(value = "LASTGANTRYHEX" )
    private String lASTGANTRYHEX;
    @JsonProperty(value = "LASTGANTRYTIME" )
    private String lASTGANTRYTIME;
    @JsonProperty(value = "OBUMAC" )
    private String oBUMAC;
    @JsonProperty(value = "OBUISSUEID" )
    private String oBUISSUEID;
    @JsonProperty(value = "OBUSN" )
    private long oBUSN;
    @JsonProperty(value = "OBUVERSION" )
    private int oBUVERSION;
    @JsonProperty(value = "OBUSTARTDATE" )
    private int oBUSTARTDATE;
    @JsonProperty(value = "OBUENDDATE" )
    private int oBUENDDATE;
    @JsonProperty(value = "OBUELECTRICAL" )
    private int oBUELECTRICAL;
    @JsonProperty(value = "OBUSTATE" )
    private String oBUSTATE;
    @JsonProperty(value = "OBUVLP" )
    private String oBUVLP;
    @JsonProperty(value = "OBUVLPC" )
    private String oBUVLPC;
    @JsonProperty(value = "OBUVEHICLETYPE" )
    private String oBUVEHICLETYPE;
    @JsonProperty(value = "VEHICLEUSERTYPE" )
    private String vEHICLEUSERTYPE;
    @JsonProperty(value = "VEHICLESEAT" )
    private String vEHICLESEAT;
    @JsonProperty(value = "AXLECOUNT" )
    private int aXLECOUNT;
    @JsonProperty(value = "TOTALWEIGHT" )
    private int tOTALWEIGHT;
    @JsonProperty(value = "VEHICLELENGTH" )
    private int vEHICLELENGTH;
    @JsonProperty(value = "VEHICLEWIDTH" )
    private int vEHICLEWIDTH;
    @JsonProperty(value = "VEHICLEHIGHT" )
    private int vEHICLEHIGHT;
    @JsonProperty(value = "CPUNETID" )
    private String cPUNETID;
    @JsonProperty(value = "CPUISSUEID" )
    private String cPUISSUEID;
    @JsonProperty(value = "CPUVLP" )
    private String cPUVLP;
    @JsonProperty(value = "CPUVLPC" )
    private String cPUVLPC;
    @JsonProperty(value = "CPUVEHICLETYPE" )
    private String cPUVEHICLETYPE;
    @JsonProperty(value = "CPUSTARTDATE" )
    private String cPUSTARTDATE;
    @JsonProperty(value = "CPUENDDATE" )
    private String cPUENDDATE;
    @JsonProperty(value = "CPUVERSION" )
    private String cPUVERSION;
    @JsonProperty(value = "CPUCARDTYPE" )
    private String cPUCARDTYPE;
    @JsonProperty(value = "CPUCARDID" )
    private String cPUCARDID;
    @JsonProperty(value = "BALANCEBEFORE" )
    private String bALANCEBEFORE;
    @JsonProperty(value = "BALANCEAFTER" )
    private String bALANCEAFTER;
    @JsonProperty(value = "GANTRYPASSCOUNT" )
    private String gANTRYPASSCOUNT;
    @JsonProperty(value = "GANTRYPASSINFO" )
    private String gANTRYPASSINFO;
    @JsonProperty(value = "FEEPROVINFO" )
    private String fEEPROVINFO;
    @JsonProperty(value = "FEESUMLOCALBEFORE" )
    private String fEESUMLOCALBEFORE;
    @JsonProperty(value = "FEESUMLOCALAFTER" )
    private String fEESUMLOCALAFTER;
    @JsonProperty(value = "FEECALCRESULT" )
    private int fEECALCRESULT;
    @JsonProperty(value = "FEEINFO1" )
    private String fEEINFO1;
    @JsonProperty(value = "FEEINFO2" )
    private String fEEINFO2;
    @JsonProperty(value = "FEEINFO3" )
    private String fEEINFO3;
    @JsonProperty(value = "HOLIDAYSTATE" )
    private String hOLIDAYSTATE;
    @JsonProperty(value = "TRADERESULT" )
    private String tRADERESULT;
    @JsonProperty(value = "SPECIALTYPE" )
    private String sPECIALTYPE;
    @JsonProperty(value = "VERIFYCODE" )
    private String vERIFYCODE;
    @JsonProperty(value = "INTERRUPTSIGNAL" )
    private String iNTERRUPTSIGNAL;
    @JsonProperty(value = "VEHICLEPICID" )
    private String vEHICLEPICID;
    @JsonProperty(value = "VEHICLETAILPICID" )
    private String vEHICLETAILPICID;
    @JsonProperty(value = "MATCHSTATUS" )
    private String mATCHSTATUS;
    @JsonProperty(value = "VALIDSTATUS" )
    private String vALIDSTATUS;
    @JsonProperty(value = "DEALSTATUS" )
    private String dEALSTATUS;
    @JsonProperty(value = "RELATEDTRADEID" )
    private String rELATEDTRADEID;
    @JsonProperty(value = "ALLRELATEDTRADEID" )
    private String aLLRELATEDTRADEID;
    @JsonProperty(value = "STATIONDBTIME" )
    private String sTATIONDBTIME;
    @JsonProperty(value = "STATIONDEALTIME" )
    private String sTATIONDEALTIME;
    @JsonProperty(value = "STATIONVALIDTIME" )
    private String sTATIONVALIDTIME;
    @JsonProperty(value = "STATIONMATCHTIME" )
    private String sTATIONMATCHTIME;
    @JsonProperty(value = "DESCRIPTION" )
    private String dESCRIPTION;
    @JsonProperty(value = "REQUESTNAME" )
    private String rEQUESTNAME;
    @JsonProperty(value = "RESPONSENAME" )
    private String rESPONSENAME;
    @JsonProperty(value = "RECEIVETIME" )
    private String rECEIVETIME;
    @JsonProperty(value = "MSGID" )
    private String mSGID;
    @JsonProperty(value = "SPARE1" )
    private String sPARE1;
    @JsonProperty(value = "SPARE2" )
    private String sPARE2;
    @JsonProperty(value = "SPARE3" )
    private String sPARE3;
    @JsonProperty(value = "SPARE4" )
    private String sPARE4;
    @JsonProperty(value = "RECORDGENTIME" )
    private String rECORDGENTIME;
    @JsonProperty(value = "VERIFYPASSTIME" )
    private String vERIFYPASSTIME;
    @JsonProperty(value = "REMARKS" )
    private String rEMARKS;
    @JsonProperty(value = "VEHICLESIGN" )
    private String vEHICLESIGN;
    @JsonProperty(value = "LASTGANTRYHEXFEE" )
    private String lASTGANTRYHEXFEE;
    @JsonProperty(value = "LASTGANTRYHEXPASS" )
    private String lASTGANTRYHEXPASS;
    @JsonProperty(value = "FEECALCSPECIAL" )
    private String fEECALCSPECIAL;
    @JsonProperty(value = "CHARGESSPECIALTYPE" )
    private String cHARGESSPECIALTYPE;
    @JsonProperty(value = "ISFIXDATA" )
    private String iSFIXDATA;
    @JsonProperty(value = "GANTRYTYPE" )
    private int gANTRYTYPE;
    @JsonProperty(value = "OBUPROVFEESUMBEFORE" )
    private String oBUPROVFEESUMBEFORE;
    @JsonProperty(value = "OBUPROVFEESUMAFTER" )
    private int oBUPROVFEESUMAFTER;
    @JsonProperty(value = "CARDFEESUMBEFORE" )
    private int cARDFEESUMBEFORE;
    @JsonProperty(value = "CARDFEESUMAFTER" )
    private int cARDFEESUMAFTER;
    @JsonProperty(value = "NOCARDTIMESBEFORE" )
    private int nOCARDTIMESBEFORE;
    @JsonProperty(value = "NOCARDTIMESAFTER" )
    private int nOCARDTIMESAFTER;
    @JsonProperty(value = "PROVINCENUMBEFORE" )
    private int pROVINCENUMBEFORE;
    @JsonProperty(value = "PROVINCENUMAFTER" )
    private int pROVINCENUMAFTER;
    @JsonProperty(value = "OBUTOTALTRADESUCCNUMBEFORE" )
    private int oBUTOTALTRADESUCCNUMBEFORE;
    @JsonProperty(value = "OBUTOTALTRADESUCCNUMAFTER" )
    private int oBUTOTALTRADESUCCNUMAFTER;
    @JsonProperty(value = "OBUPROVTRADESUCCNUMBEFORE" )
    private int oBUPROVTRADESUCCNUMBEFORE;
    @JsonProperty(value = "OBUPROVTRADESUCCNUMAFTER" )
    private int oBUPROVTRADESUCCNUMAFTER;
    @JsonProperty(value = "OBUTRADERESULT" )
    private String oBUTRADERESULT;
    @JsonProperty(value = "TRADETYPE" )
    private int tRADETYPE;
    @JsonProperty(value = "OBUINFOTYPEREAD" )
    private int oBUINFOTYPEREAD;
    @JsonProperty(value = "OBUINFOTYPEWRITE" )
    private int oBUINFOTYPEWRITE;
    @JsonProperty(value = "OBUPASSSTATE" )
    private int oBUPASSSTATE;
    @JsonProperty(value = "FEEVEHICLETYPE" )
    private int fEEVEHICLETYPE;
    @JsonProperty(value = "OBULASTGANTRYHEX" )
    private String oBULASTGANTRYHEX;
    @JsonProperty(value = "OBULASTGANTRYTIME" )
    private String oBULASTGANTRYTIME;
    @JsonProperty(value = "RATECOMPUTE" )
    private int rATECOMPUTE;
    @JsonProperty(value = "RATEFITCOUNT" )
    private String rATEFITCOUNT;
    @JsonProperty(value = "OBUPAYFEESUMBEFORE" )
    private String oBUPAYFEESUMBEFORE;
    @JsonProperty(value = "OBUPAYFEESUMAFTER" )
    private int oBUPAYFEESUMAFTER;
    @JsonProperty(value = "OBUDISCOUNTFEESUMBEFORE" )
    private int oBUDISCOUNTFEESUMBEFORE;
    @JsonProperty(value = "OBUDISCOUNTFEESUMAFTER" )
    private int oBUDISCOUNTFEESUMAFTER;
    @JsonProperty(value = "FEEMILEAGE" )
    private int fEEMILEAGE;
    @JsonProperty(value = "OBUMILEAGEBEFORE" )
    private int oBUMILEAGEBEFORE;
    @JsonProperty(value = "OBUMILEAGEAFTER" )
    private int oBUMILEAGEAFTER;
    @JsonProperty(value = "PROVMINFEE" )
    private String pROVMINFEE;
    @JsonProperty(value = "FEESPARE1" )
    private int fEESPARE1;
    @JsonProperty(value = "FEESPARE2" )
    private int fEESPARE2;
    @JsonProperty(value = "FEESPARE3BAK" )
    private String fEESPARE3BAK;
    @JsonProperty(value = "FEEPROVBEGINHEX" )
    private String fEEPROVBEGINHEX;
    @JsonProperty(value = "TRADEREADCIPHERTEXT" )
    private String tRADEREADCIPHERTEXT;
    @JsonProperty(value = "READCIPHERTEXTVERIFY" )
    private String rEADCIPHERTEXTVERIFY;
    @JsonProperty(value = "TRADEWRITECIPHERTEXT" )
    private String tRADEWRITECIPHERTEXT;
    @JsonProperty(value = "OBUFEESUMBEFORE" )
    private String oBUFEESUMBEFORE;
    @JsonProperty(value = "OBUFEESUMAFTER" )
    private int oBUFEESUMAFTER;
    @JsonProperty(value = "OBUPROVPAYFEESUMBEFORE" )
    private String oBUPROVPAYFEESUMBEFORE;
    @JsonProperty(value = "OBUPROVPAYFEESUMAFTER" )
    private int oBUPROVPAYFEESUMAFTER;
    @JsonProperty(value = "PATHFITFLAG" )
    private int pATHFITFLAG;
    @JsonProperty(value = "FEECALCSPECIALS" )
    private int fEECALCSPECIALS;
    @JsonProperty(value = "PAYFEEPROVSUMLOCAL" )
    private int pAYFEEPROVSUMLOCAL;
    @JsonProperty(value = "PCRSUVERSION" )
    private int pCRSUVERSION;
    @JsonProperty(value = "GANTRYPASSINFOAFTER" )
    private String gANTRYPASSINFOAFTER;
    @JsonProperty(value = "UPDATERESULT" )
    private int uPDATERESULT;
    @JsonProperty(value = "CPCFEETRADERESULT" )
    private int cPCFEETRADERESULT;
    @JsonProperty(value = "FEEPROVEF04" )
    private String fEEPROVEF04;
    @JsonProperty(value = "FITPROVFLAG" )
    private int fITPROVFLAG;
    @JsonProperty(value = "GANTRYPASSCOUNTBEFORE" )
    private int gANTRYPASSCOUNTBEFORE;
    @JsonProperty(value = "FEEPROVBEGINHEXFIT" )
    private String fEEPROVBEGINHEXFIT;
    @JsonProperty(value = "FEEPROVBEGINTIMEFIT" )
    private String fEEPROVBEGINTIMEFIT;
    @JsonProperty(value = "TOLLINTERVALSIGN" )
    private int tOLLINTERVALSIGN;
    @JsonProperty(value = "PROVMINFEECALCMODE" )
    private String pROVMINFEECALCMODE;
    @JsonProperty(value = "FEEPROVBEGINTIME" )
    private String fEEPROVBEGINTIME;
    @JsonProperty(value = "FEESUMLOCALAFTEREF04" )
    private String fEESUMLOCALAFTEREF04;
    @JsonProperty(value = "LASTGANTRYFEEPASS" )
    private String lASTGANTRYFEEPASS;
    @JsonProperty(value = "LASTGANTRYMILEPASS" )
    private String lASTGANTRYMILEPASS;
    @JsonProperty(value = "RSUMANUID" )
    private String rSUMANUID;
    @JsonProperty(value = "FEEDATAVERSION" )
    private String fEEDATAVERSION;
    @JsonProperty(value = "GANTRYHEXOPPOTIME" )
    private String gANTRYHEXOPPOTIME;
    @JsonProperty(value = "OBUPAYFEESUMAFTERNOFIT" )
    private String oBUPAYFEESUMAFTERNOFIT;
    @JsonProperty(value = "OBUFEESUMAFTERNOFIT" )
    private String oBUFEESUMAFTERNOFIT;
    @JsonProperty(value = "OBUMILEAGEAFTERNOFIT" )
    private String oBUMILEAGEAFTERNOFIT;
    @JsonProperty(value = "OBUVEHICLEUSERTYPE" )
    private String oBUVEHICLEUSERTYPE;
    @JsonProperty(value = "FEEPROVBEGINHEXBEFORE" )
    private String fEEPROVBEGINHEXBEFORE;
    @JsonProperty(value = "GANTRYFITRESULT" )
    private String gANTRYFITRESULT;
    @JsonProperty(value = "GANTRYFITVERSION" )
    private String gANTRYFITVERSION;
    @JsonProperty(value = "GANTRYFITHEXS" )
    private String gANTRYFITHEXS;
    @JsonProperty(value = "GANTRYFITTOLLS" )
    private String gANTRYFITTOLLS;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEE" )
    private String gANTRYFITNPROVPAYFEE;
    @JsonProperty(value = "GANTRYFITNPROVDISCONTFEE" )
    private String gANTRYFITNPROVDISCONTFEE;
    @JsonProperty(value = "GANTRYFITNPROVREALFEE" )
    private String gANTRYFITNPROVREALFEE;
    @JsonProperty(value = "GANTRYFITNPROVMILES" )
    private String gANTRYFITNPROVMILES;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEEGROUP" )
    private String gANTRYFITNPROVPAYFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVDISCONTFEEGROUP" )
    private String gANTRYFITNPROVDISCONTFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVREALFEEGROUP" )
    private String gANTRYFITNPROVREALFEEGROUP;
    @JsonProperty(value = "GANTRYFITNPROVPAYFEESUM" )
    private String gANTRYFITNPROVPAYFEESUM;
    @JsonProperty(value = "GANTRYFITNPROVREALFEESUM" )
    private String gANTRYFITNPROVREALFEESUM;
    @JsonProperty(value = "GANTRYFITNPROVMILESUM" )
    private String gANTRYFITNPROVMILESUM;
    @JsonProperty(value = "GANTRYFITNPROVPREREALFEE" )
    private String gANTRYFITNPROVPREREALFEE;
    @JsonProperty(value = "PATHFITDESC" )
    private String pATHFITDESC;
    @JsonProperty(value = "GANTRYFITNEIGHBORPROVCODE" )
    private String gANTRYFITNEIGHBORPROVCODE;
    @JsonProperty(value = "FEESPARE3" )
    private String fEESPARE3;

    public boolean peekETC() {
        return this.getMEDIATYPE() != 1;
    }

    @Override
    public String peekTime() {
        return tRANSTIME;
    }
}
