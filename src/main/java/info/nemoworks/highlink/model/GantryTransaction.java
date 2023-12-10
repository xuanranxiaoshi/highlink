package info.nemoworks.highlink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;

import com.esotericsoftware.kryo.util.ObjectMap;

public class GantryTransaction implements Transaction {
    @JsonProperty("TRADEID")
    public String tRADEID;
    @JsonProperty("GANTRYID")
    public String gANTRYID;
    @JsonProperty("ORIGINALFLAG")
    public int oRIGINALFLAG;
    @JsonProperty("COMPUTERORDER")
    public int cOMPUTERORDER;
    @JsonProperty("HOURBATCHNO")
    public String hOURBATCHNO;
    @JsonProperty("GANTRYORDERNUM")
    public int gANTRYORDERNUM;
    @JsonProperty("GANTRYHEX")
    public String gANTRYHEX;
    @JsonProperty("GANTRYHEXOPPOSITE")
    public String gANTRYHEXOPPOSITE;
    @JsonProperty("TRANSTIME")
    public String tRANSTIME;
    @JsonProperty("PAYFEE")
    public int pAYFEE;
    @JsonProperty("FEE")
    public int fEE;
    @JsonProperty("DISCOUNTFEE")
    public int dISCOUNTFEE;
    @JsonProperty("TRANSFEE")
    public int tRANSFEE;
    @JsonProperty("MEDIATYPE")
    public int mEDIATYPE;
    @JsonProperty("OBUSIGN")
    public int oBUSIGN;
    @JsonProperty("TOLLINTERVALID")
    public String tOLLINTERVALID;
    @JsonProperty("PAYFEEGROUP")
    public int pAYFEEGROUP;
    @JsonProperty("FEEGROUP")
    public int fEEGROUP;
    @JsonProperty("DISCOUNTFEEGROUP")
    public int dISCOUNTFEEGROUP;
    @JsonProperty("ENWEIGHT")
    public int eNWEIGHT;
    @JsonProperty("ENAXLECOUNT")
    public int eNAXLECOUNT;
    @JsonProperty("VLP")
    public String vLP;
    @JsonProperty("VLPC")
    public int vLPC;
    @JsonProperty("VEHICLETYPE")
    public int vEHICLETYPE;
    @JsonProperty("IDENTIFYVEHICLETYPE")
    public String iDENTIFYVEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    public int vEHICLECLASS;
    @JsonProperty("TAC")
    public String tAC;
    @JsonProperty("TRANSTYPE")
    public String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    public String tERMINALNO;
    @JsonProperty("TERMINALTRANSNO")
    public String tERMINALTRANSNO;
    @JsonProperty("TRANSNO")
    public int tRANSNO;
    @JsonProperty("SERVICETYPE")
    public int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    public int aLGORITHMIDENTIFIER;
    @JsonProperty("KEYVERSION")
    public int kEYVERSION;
    @JsonProperty("ANTENNAID")
    public int aNTENNAID;
    @JsonProperty("TOLLMODEVER")
    public long tOLLMODEVER;
    @JsonProperty("TOLLPARAVER")
    public long tOLLPARAVER;
    @JsonProperty("CONSUMETIME")
    public int cONSUMETIME;
    @JsonProperty("PASSSTATE")
    public int pASSSTATE;
    @JsonProperty("ENTOLLLANEID")
    public String eNTOLLLANEID;
    @JsonProperty("ENTOLLSTATIONHEX")
    public int eNTOLLSTATIONHEX;
    @JsonProperty("ENTIME")
    public String eNTIME;
    @JsonProperty("ENLANETYPE")
    public int eNLANETYPE;
    @JsonProperty("PASSID")
    public String pASSID;
    @JsonProperty("LASTGANTRYHEX")
    public String lASTGANTRYHEX;
    @JsonProperty("LASTGANTRYTIME")
    public String lASTGANTRYTIME;
    @JsonProperty("OBUMAC")
    public String oBUMAC;
    @JsonProperty("OBUISSUEID")
    public String oBUISSUEID;
    @JsonProperty("OBUSN")
    public long oBUSN;
    @JsonProperty("OBUVERSION")
    public int oBUVERSION;
    @JsonProperty("OBUSTARTDATE")
    public int oBUSTARTDATE;
    @JsonProperty("OBUENDDATE")
    public int oBUENDDATE;
    @JsonProperty("OBUELECTRICAL")
    public int oBUELECTRICAL;
    @JsonProperty("OBUSTATE")
    public String oBUSTATE;
    @JsonProperty("OBUVLP")
    public String oBUVLP;
    @JsonProperty("OBUVLPC")
    public int oBUVLPC;
    @JsonProperty("OBUVEHICLETYPE")
    public int oBUVEHICLETYPE;
    @JsonProperty("VEHICLEUSERTYPE")
    public int vEHICLEUSERTYPE;
    @JsonProperty("VEHICLESEAT")
    public int vEHICLESEAT;
    @JsonProperty("AXLECOUNT")
    public int aXLECOUNT;
    @JsonProperty("TOTALWEIGHT")
    public int tOTALWEIGHT;
    @JsonProperty("VEHICLELENGTH")
    public int vEHICLELENGTH;
    @JsonProperty("VEHICLEWIDTH")
    public int vEHICLEWIDTH;
    @JsonProperty("VEHICLEHIGHT")
    public int vEHICLEHIGHT;
    @JsonProperty("CPUNETID")
    public int cPUNETID;
    @JsonProperty("CPUISSUEID")
    public String cPUISSUEID;
    @JsonProperty("CPUVLP")
    public String cPUVLP;
    @JsonProperty("CPUVLPC")
    public int cPUVLPC;
    @JsonProperty("CPUVEHICLETYPE")
    public int cPUVEHICLETYPE;
    @JsonProperty("CPUSTARTDATE")
    public int cPUSTARTDATE;
    @JsonProperty("CPUENDDATE")
    public int cPUENDDATE;
    @JsonProperty("CPUVERSION")
    public int cPUVERSION;
    @JsonProperty("CPUCARDTYPE")
    public int cPUCARDTYPE;
    @JsonProperty("CPUCARDID")
    public long cPUCARDID;
    @JsonProperty("BALANCEBEFORE")
    public int bALANCEBEFORE;
    @JsonProperty("BALANCEAFTER")
    public int bALANCEAFTER;
    @JsonProperty("GANTRYPASSCOUNT")
    public int gANTRYPASSCOUNT;
    @JsonProperty("GANTRYPASSINFO")
    public String gANTRYPASSINFO;
    @JsonProperty("FEEPROVINFO")
    public String fEEPROVINFO;
    @JsonProperty("FEESUMLOCALBEFORE")
    public int fEESUMLOCALBEFORE;
    @JsonProperty("FEESUMLOCALAFTER")
    public int fEESUMLOCALAFTER;
    @JsonProperty("FEECALCRESULT")
    public int fEECALCRESULT;
    @JsonProperty("FEEINFO1")
    public String fEEINFO1;
    @JsonProperty("FEEINFO2")
    public int fEEINFO2;
    @JsonProperty("FEEINFO3")
    public String fEEINFO3;
    @JsonProperty("HOLIDAYSTATE")
    public String hOLIDAYSTATE;
    @JsonProperty("TRADERESULT")
    public int tRADERESULT;
    @JsonProperty("SPECIALTYPE")
    public String sPECIALTYPE;
    @JsonProperty("VERIFYCODE")
    public int vERIFYCODE;
    @JsonProperty("INTERRUPTSIGNAL")
    public int iNTERRUPTSIGNAL;
    @JsonProperty("VEHICLEPICID")
    public String vEHICLEPICID;
    @JsonProperty("VEHICLETAILPICID")
    public int vEHICLETAILPICID;
    @JsonProperty("MATCHSTATUS")
    public int mATCHSTATUS;
    @JsonProperty("VALIDSTATUS")
    public String vALIDSTATUS;
    @JsonProperty("DEALSTATUS")
    public String dEALSTATUS;
    @JsonProperty("RELATEDTRADEID")
    public int rELATEDTRADEID;
    @JsonProperty("ALLRELATEDTRADEID")
    public int aLLRELATEDTRADEID;
    @JsonProperty("STATIONDBTIME")
    public int sTATIONDBTIME;
    @JsonProperty("STATIONDEALTIME")
    public String sTATIONDEALTIME;
    @JsonProperty("STATIONVALIDTIME")
    public String sTATIONVALIDTIME;
    @JsonProperty("STATIONMATCHTIME")
    public String sTATIONMATCHTIME;
    @JsonProperty("DESCRIPTION")
    public String dESCRIPTION;
    @JsonProperty("REQUESTNAME")
    public String rEQUESTNAME;
    @JsonProperty("RESPONSENAME")
    public String rESPONSENAME;
    @JsonProperty("RECEIVETIME")
    public int rECEIVETIME;
    @JsonProperty("MSGID")
    public String mSGID;
    @JsonProperty("SPARE1")
    public String sPARE1;
    @JsonProperty("SPARE2")
    public String sPARE2;
    @JsonProperty("SPARE3")
    public String sPARE3;
    @JsonProperty("SPARE4")
    public String sPARE4;
    @JsonProperty("RECORDGENTIME")
    public String rECORDGENTIME;
    @JsonProperty("VERIFYPASSTIME")
    public String vERIFYPASSTIME;
    @JsonProperty("REMARKS")
    public int rEMARKS;
    @JsonProperty("VEHICLESIGN")
    public String vEHICLESIGN;
    @JsonProperty("LASTGANTRYHEXFEE")
    public String lASTGANTRYHEXFEE;
    @JsonProperty("LASTGANTRYHEXPASS")
    public String lASTGANTRYHEXPASS;
    @JsonProperty("FEECALCSPECIAL")
    public String fEECALCSPECIAL;
    @JsonProperty("CHARGESSPECIALTYPE")
    public String cHARGESSPECIALTYPE;
    @JsonProperty("ISFIXDATA")
    public String iSFIXDATA;
    @JsonProperty("GANTRYTYPE")
    public int gANTRYTYPE;
    @JsonProperty("OBUPROVFEESUMBEFORE")
    public String oBUPROVFEESUMBEFORE;
    @JsonProperty("OBUPROVFEESUMAFTER")
    public int oBUPROVFEESUMAFTER;
    @JsonProperty("CARDFEESUMBEFORE")
    public int cARDFEESUMBEFORE;
    @JsonProperty("CARDFEESUMAFTER")
    public int cARDFEESUMAFTER;
    @JsonProperty("NOCARDTIMESBEFORE")
    public int nOCARDTIMESBEFORE;
    @JsonProperty("NOCARDTIMESAFTER")
    public int nOCARDTIMESAFTER;
    @JsonProperty("PROVINCENUMBEFORE")
    public int pROVINCENUMBEFORE;
    @JsonProperty("PROVINCENUMAFTER")
    public int pROVINCENUMAFTER;
    @JsonProperty("OBUTOTALTRADESUCCNUMBEFORE")
    public int oBUTOTALTRADESUCCNUMBEFORE;
    @JsonProperty("OBUTOTALTRADESUCCNUMAFTER")
    public int oBUTOTALTRADESUCCNUMAFTER;
    @JsonProperty("OBUPROVTRADESUCCNUMBEFORE")
    public int oBUPROVTRADESUCCNUMBEFORE;
    @JsonProperty("OBUPROVTRADESUCCNUMAFTER")
    public int oBUPROVTRADESUCCNUMAFTER;
    @JsonProperty("OBUTRADERESULT")
    public int oBUTRADERESULT;
    @JsonProperty("TRADETYPE")
    public int tRADETYPE;
    @JsonProperty("OBUINFOTYPEREAD")
    public int oBUINFOTYPEREAD;
    @JsonProperty("OBUINFOTYPEWRITE")
    public int oBUINFOTYPEWRITE;
    @JsonProperty("OBUPASSSTATE")
    public int oBUPASSSTATE;
    @JsonProperty("FEEVEHICLETYPE")
    public int fEEVEHICLETYPE;
    @JsonProperty("OBULASTGANTRYHEX")
    public int oBULASTGANTRYHEX;
    @JsonProperty("OBULASTGANTRYTIME")
    public int oBULASTGANTRYTIME;
    @JsonProperty("RATECOMPUTE")
    public int rATECOMPUTE;
    @JsonProperty("RATEFITCOUNT")
    public String rATEFITCOUNT;
    @JsonProperty("OBUPAYFEESUMBEFORE")
    public String oBUPAYFEESUMBEFORE;
    @JsonProperty("OBUPAYFEESUMAFTER")
    public int oBUPAYFEESUMAFTER;
    @JsonProperty("OBUDISCOUNTFEESUMBEFORE")
    public int oBUDISCOUNTFEESUMBEFORE;
    @JsonProperty("OBUDISCOUNTFEESUMAFTER")
    public int oBUDISCOUNTFEESUMAFTER;
    @JsonProperty("FEEMILEAGE")
    public int fEEMILEAGE;
    @JsonProperty("OBUMILEAGEBEFORE")
    public int oBUMILEAGEBEFORE;
    @JsonProperty("OBUMILEAGEAFTER")
    public int oBUMILEAGEAFTER;
    @JsonProperty("PROVMINFEE")
    public int pROVMINFEE;
    @JsonProperty("FEESPARE1")
    public int fEESPARE1;
    @JsonProperty("FEESPARE2")
    public int fEESPARE2;
    @JsonProperty("FEESPARE3BAK")
    public String fEESPARE3BAK;
    @JsonProperty("FEEPROVBEGINHEX")
    public String fEEPROVBEGINHEX;
    @JsonProperty("TRADEREADCIPHERTEXT")
    public String tRADEREADCIPHERTEXT;
    @JsonProperty("READCIPHERTEXTVERIFY")
    public String rEADCIPHERTEXTVERIFY;
    @JsonProperty("TRADEWRITECIPHERTEXT")
    public String tRADEWRITECIPHERTEXT;
    @JsonProperty("OBUFEESUMBEFORE")
    public String oBUFEESUMBEFORE;
    @JsonProperty("OBUFEESUMAFTER")
    public int oBUFEESUMAFTER;
    @JsonProperty("OBUPROVPAYFEESUMBEFORE")
    public String oBUPROVPAYFEESUMBEFORE;
    @JsonProperty("OBUPROVPAYFEESUMAFTER")
    public int oBUPROVPAYFEESUMAFTER;
    @JsonProperty("PATHFITFLAG")
    public int pATHFITFLAG;
    @JsonProperty("FEECALCSPECIALS")
    public int fEECALCSPECIALS;
    @JsonProperty("PAYFEEPROVSUMLOCAL")
    public int pAYFEEPROVSUMLOCAL;
    @JsonProperty("PCRSUVERSION")
    public int pCRSUVERSION;
    @JsonProperty("GANTRYPASSINFOAFTER")
    public int gANTRYPASSINFOAFTER;
    @JsonProperty("UPDATERESULT")
    public int uPDATERESULT;
    @JsonProperty("CPCFEETRADERESULT")
    public int cPCFEETRADERESULT;
    @JsonProperty("FEEPROVEF04")
    public String fEEPROVEF04;
    @JsonProperty("FITPROVFLAG")
    public int fITPROVFLAG;
    @JsonProperty("GANTRYPASSCOUNTBEFORE")
    public int gANTRYPASSCOUNTBEFORE;
    @JsonProperty("FEEPROVBEGINHEXFIT")
    public int fEEPROVBEGINHEXFIT;
    @JsonProperty("FEEPROVBEGINTIMEFIT")
    public int fEEPROVBEGINTIMEFIT;
    @JsonProperty("TOLLINTERVALSIGN")
    public int tOLLINTERVALSIGN;
    @JsonProperty("PROVMINFEECALCMODE")
    public String pROVMINFEECALCMODE;
    @JsonProperty("FEEPROVBEGINTIME")
    public String fEEPROVBEGINTIME;
    @JsonProperty("FEESUMLOCALAFTEREF04")
    public int fEESUMLOCALAFTEREF04;
    @JsonProperty("LASTGANTRYFEEPASS")
    public String lASTGANTRYFEEPASS;
    @JsonProperty("LASTGANTRYMILEPASS")
    public String lASTGANTRYMILEPASS;
    @JsonProperty("RSUMANUID")
    public String rSUMANUID;
    @JsonProperty("FEEDATAVERSION")
    public String fEEDATAVERSION;
    @JsonProperty("GANTRYHEXOPPOTIME")
    public String gANTRYHEXOPPOTIME;
    @JsonProperty("OBUPAYFEESUMAFTERNOFIT")
    public String oBUPAYFEESUMAFTERNOFIT;
    @JsonProperty("OBUFEESUMAFTERNOFIT")
    public String oBUFEESUMAFTERNOFIT;
    @JsonProperty("OBUMILEAGEAFTERNOFIT")
    public String oBUMILEAGEAFTERNOFIT;
    @JsonProperty("OBUVEHICLEUSERTYPE")
    public String oBUVEHICLEUSERTYPE;
    @JsonProperty("FEEPROVBEGINHEXBEFORE")
    public String fEEPROVBEGINHEXBEFORE;
    @JsonProperty("GANTRYFITRESULT")
    public String gANTRYFITRESULT;
    @JsonProperty("GANTRYFITVERSION")
    public String gANTRYFITVERSION;
    @JsonProperty("GANTRYFITHEXS")
    public String gANTRYFITHEXS;
    @JsonProperty("GANTRYFITTOLLS")
    public String gANTRYFITTOLLS;
    @JsonProperty("GANTRYFITNPROVPAYFEE")
    public String gANTRYFITNPROVPAYFEE;
    @JsonProperty("GANTRYFITNPROVDISCONTFEE")
    public String gANTRYFITNPROVDISCONTFEE;
    @JsonProperty("GANTRYFITNPROVREALFEE")
    public String gANTRYFITNPROVREALFEE;
    @JsonProperty("GANTRYFITNPROVMILES")
    public String gANTRYFITNPROVMILES;
    @JsonProperty("GANTRYFITNPROVPAYFEEGROUP")
    public String gANTRYFITNPROVPAYFEEGROUP;
    @JsonProperty("GANTRYFITNPROVDISCONTFEEGROUP")
    public String gANTRYFITNPROVDISCONTFEEGROUP;
    @JsonProperty("GANTRYFITNPROVREALFEEGROUP")
    public String gANTRYFITNPROVREALFEEGROUP;
    @JsonProperty("GANTRYFITNPROVPAYFEESUM")
    public String gANTRYFITNPROVPAYFEESUM;
    @JsonProperty("GANTRYFITNPROVREALFEESUM")
    public String gANTRYFITNPROVREALFEESUM;
    @JsonProperty("GANTRYFITNPROVMILESUM")
    public String gANTRYFITNPROVMILESUM;
    @JsonProperty("GANTRYFITNPROVPREREALFEE")
    public String gANTRYFITNPROVPREREALFEE;
    @JsonProperty("PATHFITDESC")
    public String pATHFITDESC;
    @JsonProperty("GANTRYFITNEIGHBORPROVCODE")
    public String gANTRYFITNEIGHBORPROVCODE;
    @JsonProperty("FEESPARE3")
    public String fEESPARE3;

    @Override
    public String getID() {
        return this.tRADEID;
    }

    public static GantryTransaction fromJson(ObjectNode json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.treeToValue(json, GantryTransaction.class);
    }
}
