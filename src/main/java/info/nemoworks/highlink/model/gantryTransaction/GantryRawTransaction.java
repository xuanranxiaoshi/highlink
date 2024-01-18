package info.nemoworks.highlink.model.gantryTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;
import info.nemoworks.highlink.model.PathTransaction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GantryRawTransaction implements HighwayTransaction, PathTransaction {
    @JsonProperty("TRADEID")
    private String tRADEID;
    @JsonProperty("GANTRYID")
    private String gANTRYID;
    @JsonProperty("ORIGINALFLAG")
    private int oRIGINALFLAG;
    @JsonProperty("COMPUTERORDER")
    private int cOMPUTERORDER;
    @JsonProperty("HOURBATCHNO")
    private String hOURBATCHNO;
    @JsonProperty("GANTRYORDERNUM")
    private int gANTRYORDERNUM;
    @JsonProperty("GANTRYHEX")
    private String gANTRYHEX;
    @JsonProperty("GANTRYHEXOPPOSITE")
    private String gANTRYHEXOPPOSITE;
    @JsonProperty("TRANSTIME")
    private String tRANSTIME;
    @JsonProperty("PAYFEE")
    private int pAYFEE;
    @JsonProperty("FEE")
    private int fEE;
    @JsonProperty("DISCOUNTFEE")
    private int dISCOUNTFEE;
    @JsonProperty("TRANSFEE")
    private int tRANSFEE;
    @JsonProperty("MEDIATYPE")
    private int mEDIATYPE;
    @JsonProperty("OBUSIGN")
    private String oBUSIGN;
    @JsonProperty("TOLLINTERVALID")
    private String tOLLINTERVALID;
    @JsonProperty("PAYFEEGROUP")
    private String pAYFEEGROUP;
    @JsonProperty("FEEGROUP")
    private String fEEGROUP;
    @JsonProperty("DISCOUNTFEEGROUP")
    private String dISCOUNTFEEGROUP;
    @JsonProperty("ENWEIGHT")
    private String eNWEIGHT;
    @JsonProperty("ENAXLECOUNT")
    private String eNAXLECOUNT;
    @JsonProperty("VLP")
    private String vLP;
    @JsonProperty("VLPC")
    private int vLPC;
    @JsonProperty("VEHICLETYPE")
    private String vEHICLETYPE;
    @JsonProperty("IDENTIFYVEHICLETYPE")
    private String iDENTIFYVEHICLETYPE;
    @JsonProperty("VEHICLECLASS")
    private int vEHICLECLASS;
    @JsonProperty("TAC")
    private String tAC;
    @JsonProperty("TRANSTYPE")
    private String tRANSTYPE;
    @JsonProperty("TERMINALNO")
    private String tERMINALNO;
    @JsonProperty("TERMINALTRANSNO")
    private String tERMINALTRANSNO;
    @JsonProperty("TRANSNO")
    private String tRANSNO;
    @JsonProperty("SERVICETYPE")
    private int sERVICETYPE;
    @JsonProperty("ALGORITHMIDENTIFIER")
    private int aLGORITHMIDENTIFIER;
    @JsonProperty("KEYVERSION")
    private int kEYVERSION;
    @JsonProperty("ANTENNAID")
    private int aNTENNAID;
    @JsonProperty("TOLLMODEVER")
    private long tOLLMODEVER;
    @JsonProperty("TOLLPARAVER")
    private long tOLLPARAVER;
    @JsonProperty("CONSUMETIME")
    private int cONSUMETIME;
    @JsonProperty("PASSSTATE")
    private int pASSSTATE;
    @JsonProperty("ENTOLLLANEID")
    private String eNTOLLLANEID;
    @JsonProperty("ENTOLLSTATIONHEX")
    private String eNTOLLSTATIONHEX;
    @JsonProperty("ENTIME")
    private String eNTIME;
    @JsonProperty("ENLANETYPE")
    private int eNLANETYPE;
    @JsonProperty("PASSID")
    private String pASSID;
    @JsonProperty("LASTGANTRYHEX")
    private String lASTGANTRYHEX;
    @JsonProperty("LASTGANTRYTIME")
    private String lASTGANTRYTIME;
    @JsonProperty("OBUMAC")
    private String oBUMAC;
    @JsonProperty("OBUISSUEID")
    private String oBUISSUEID;
    @JsonProperty("OBUSN")
    private long oBUSN;
    @JsonProperty("OBUVERSION")
    private int oBUVERSION;
    @JsonProperty("OBUSTARTDATE")
    private int oBUSTARTDATE;
    @JsonProperty("OBUENDDATE")
    private int oBUENDDATE;
    @JsonProperty("OBUELECTRICAL")
    private int oBUELECTRICAL;
    @JsonProperty("OBUSTATE")
    private String oBUSTATE;
    @JsonProperty("OBUVLP")
    private String oBUVLP;
    @JsonProperty("OBUVLPC")
    private String oBUVLPC;
    @JsonProperty("OBUVEHICLETYPE")
    private String oBUVEHICLETYPE;
    @JsonProperty("VEHICLEUSERTYPE")
    private String vEHICLEUSERTYPE;
    @JsonProperty("VEHICLESEAT")
    private String vEHICLESEAT;
    @JsonProperty("AXLECOUNT")
    private int aXLECOUNT;
    @JsonProperty("TOTALWEIGHT")
    private int tOTALWEIGHT;
    @JsonProperty("VEHICLELENGTH")
    private int vEHICLELENGTH;
    @JsonProperty("VEHICLEWIDTH")
    private int vEHICLEWIDTH;
    @JsonProperty("VEHICLEHIGHT")
    private int vEHICLEHIGHT;
    @JsonProperty("CPUNETID")
    private String cPUNETID;
    @JsonProperty("CPUISSUEID")
    private String cPUISSUEID;
    @JsonProperty("CPUVLP")
    private String cPUVLP;
    @JsonProperty("CPUVLPC")
    private String cPUVLPC;
    @JsonProperty("CPUVEHICLETYPE")
    private String cPUVEHICLETYPE;
    @JsonProperty("CPUSTARTDATE")
    private String cPUSTARTDATE;
    @JsonProperty("CPUENDDATE")
    private String cPUENDDATE;
    @JsonProperty("CPUVERSION")
    private String cPUVERSION;
    @JsonProperty("CPUCARDTYPE")
    private String cPUCARDTYPE;
    @JsonProperty("CPUCARDID")
    private String cPUCARDID;
    @JsonProperty("BALANCEBEFORE")
    private String bALANCEBEFORE;
    @JsonProperty("BALANCEAFTER")
    private String bALANCEAFTER;
    @JsonProperty("GANTRYPASSCOUNT")
    private String gANTRYPASSCOUNT;
    @JsonProperty("GANTRYPASSINFO")
    private String gANTRYPASSINFO;
    @JsonProperty("FEEPROVINFO")
    private String fEEPROVINFO;
    @JsonProperty("FEESUMLOCALBEFORE")
    private String fEESUMLOCALBEFORE;
    @JsonProperty("FEESUMLOCALAFTER")
    private String fEESUMLOCALAFTER;
    @JsonProperty("FEECALCRESULT")
    private int fEECALCRESULT;
    @JsonProperty("FEEINFO1")
    private String fEEINFO1;
    @JsonProperty("FEEINFO2")
    private String fEEINFO2;
    @JsonProperty("FEEINFO3")
    private String fEEINFO3;
    @JsonProperty("HOLIDAYSTATE")
    private String hOLIDAYSTATE;
    @JsonProperty("TRADERESULT")
    private String tRADERESULT;
    @JsonProperty("SPECIALTYPE")
    private String sPECIALTYPE;
    @JsonProperty("VERIFYCODE")
    private String vERIFYCODE;
    @JsonProperty("INTERRUPTSIGNAL")
    private String iNTERRUPTSIGNAL;
    @JsonProperty("VEHICLEPICID")
    private String vEHICLEPICID;
    @JsonProperty("VEHICLETAILPICID")
    private String vEHICLETAILPICID;
    @JsonProperty("MATCHSTATUS")
    private String mATCHSTATUS;
    @JsonProperty("VALIDSTATUS")
    private String vALIDSTATUS;
    @JsonProperty("DEALSTATUS")
    private String dEALSTATUS;
    @JsonProperty("RELATEDTRADEID")
    private String rELATEDTRADEID;
    @JsonProperty("ALLRELATEDTRADEID")
    private String aLLRELATEDTRADEID;
    @JsonProperty("STATIONDBTIME")
    private String sTATIONDBTIME;
    @JsonProperty("STATIONDEALTIME")
    private String sTATIONDEALTIME;
    @JsonProperty("STATIONVALIDTIME")
    private String sTATIONVALIDTIME;
    @JsonProperty("STATIONMATCHTIME")
    private String sTATIONMATCHTIME;
    @JsonProperty("DESCRIPTION")
    private String dESCRIPTION;
    @JsonProperty("REQUESTNAME")
    private String rEQUESTNAME;
    @JsonProperty("RESPONSENAME")
    private String rESPONSENAME;
    @JsonProperty("RECEIVETIME")
    private String rECEIVETIME;
    @JsonProperty("MSGID")
    private String mSGID;
    @JsonProperty("SPARE1")
    private String sPARE1;
    @JsonProperty("SPARE2")
    private String sPARE2;
    @JsonProperty("SPARE3")
    private String sPARE3;
    @JsonProperty("SPARE4")
    private String sPARE4;
    @JsonProperty("RECORDGENTIME")
    private String rECORDGENTIME;
    @JsonProperty("VERIFYPASSTIME")
    private String vERIFYPASSTIME;
    @JsonProperty("REMARKS")
    private String rEMARKS;
    @JsonProperty("VEHICLESIGN")
    private String vEHICLESIGN;
    @JsonProperty("LASTGANTRYHEXFEE")
    private String lASTGANTRYHEXFEE;
    @JsonProperty("LASTGANTRYHEXPASS")
    private String lASTGANTRYHEXPASS;
    @JsonProperty("FEECALCSPECIAL")
    private String fEECALCSPECIAL;
    @JsonProperty("CHARGESSPECIALTYPE")
    private String cHARGESSPECIALTYPE;
    @JsonProperty("ISFIXDATA")
    private String iSFIXDATA;
    @JsonProperty("GANTRYTYPE")
    private int gANTRYTYPE;
    @JsonProperty("OBUPROVFEESUMBEFORE")
    private String oBUPROVFEESUMBEFORE;
    @JsonProperty("OBUPROVFEESUMAFTER")
    private int oBUPROVFEESUMAFTER;
    @JsonProperty("CARDFEESUMBEFORE")
    private int cARDFEESUMBEFORE;
    @JsonProperty("CARDFEESUMAFTER")
    private int cARDFEESUMAFTER;
    @JsonProperty("NOCARDTIMESBEFORE")
    private int nOCARDTIMESBEFORE;
    @JsonProperty("NOCARDTIMESAFTER")
    private int nOCARDTIMESAFTER;
    @JsonProperty("PROVINCENUMBEFORE")
    private int pROVINCENUMBEFORE;
    @JsonProperty("PROVINCENUMAFTER")
    private int pROVINCENUMAFTER;
    @JsonProperty("OBUTOTALTRADESUCCNUMBEFORE")
    private int oBUTOTALTRADESUCCNUMBEFORE;
    @JsonProperty("OBUTOTALTRADESUCCNUMAFTER")
    private int oBUTOTALTRADESUCCNUMAFTER;
    @JsonProperty("OBUPROVTRADESUCCNUMBEFORE")
    private int oBUPROVTRADESUCCNUMBEFORE;
    @JsonProperty("OBUPROVTRADESUCCNUMAFTER")
    private int oBUPROVTRADESUCCNUMAFTER;
    @JsonProperty("OBUTRADERESULT")
    private String oBUTRADERESULT;
    @JsonProperty("TRADETYPE")
    private int tRADETYPE;
    @JsonProperty("OBUINFOTYPEREAD")
    private int oBUINFOTYPEREAD;
    @JsonProperty("OBUINFOTYPEWRITE")
    private int oBUINFOTYPEWRITE;
    @JsonProperty("OBUPASSSTATE")
    private int oBUPASSSTATE;
    @JsonProperty("FEEVEHICLETYPE")
    private int fEEVEHICLETYPE;
    @JsonProperty("OBULASTGANTRYHEX")
    private String oBULASTGANTRYHEX;
    @JsonProperty("OBULASTGANTRYTIME")
    private String oBULASTGANTRYTIME;
    @JsonProperty("RATECOMPUTE")
    private int rATECOMPUTE;
    @JsonProperty("RATEFITCOUNT")
    private String rATEFITCOUNT;
    @JsonProperty("OBUPAYFEESUMBEFORE")
    private String oBUPAYFEESUMBEFORE;
    @JsonProperty("OBUPAYFEESUMAFTER")
    private int oBUPAYFEESUMAFTER;
    @JsonProperty("OBUDISCOUNTFEESUMBEFORE")
    private int oBUDISCOUNTFEESUMBEFORE;
    @JsonProperty("OBUDISCOUNTFEESUMAFTER")
    private int oBUDISCOUNTFEESUMAFTER;
    @JsonProperty("FEEMILEAGE")
    private int fEEMILEAGE;
    @JsonProperty("OBUMILEAGEBEFORE")
    private int oBUMILEAGEBEFORE;
    @JsonProperty("OBUMILEAGEAFTER")
    private int oBUMILEAGEAFTER;
    @JsonProperty("PROVMINFEE")
    private String pROVMINFEE;
    @JsonProperty("FEESPARE1")
    private int fEESPARE1;
    @JsonProperty("FEESPARE2")
    private int fEESPARE2;
    @JsonProperty("FEESPARE3BAK")
    private String fEESPARE3BAK;
    @JsonProperty("FEEPROVBEGINHEX")
    private String fEEPROVBEGINHEX;
    @JsonProperty("TRADEREADCIPHERTEXT")
    private String tRADEREADCIPHERTEXT;
    @JsonProperty("READCIPHERTEXTVERIFY")
    private String rEADCIPHERTEXTVERIFY;
    @JsonProperty("TRADEWRITECIPHERTEXT")
    private String tRADEWRITECIPHERTEXT;
    @JsonProperty("OBUFEESUMBEFORE")
    private String oBUFEESUMBEFORE;
    @JsonProperty("OBUFEESUMAFTER")
    private int oBUFEESUMAFTER;
    @JsonProperty("OBUPROVPAYFEESUMBEFORE")
    private String oBUPROVPAYFEESUMBEFORE;
    @JsonProperty("OBUPROVPAYFEESUMAFTER")
    private int oBUPROVPAYFEESUMAFTER;
    @JsonProperty("PATHFITFLAG")
    private int pATHFITFLAG;
    @JsonProperty("FEECALCSPECIALS")
    private int fEECALCSPECIALS;
    @JsonProperty("PAYFEEPROVSUMLOCAL")
    private int pAYFEEPROVSUMLOCAL;
    @JsonProperty("PCRSUVERSION")
    private int pCRSUVERSION;
    @JsonProperty("GANTRYPASSINFOAFTER")
    private String gANTRYPASSINFOAFTER;
    @JsonProperty("UPDATERESULT")
    private int uPDATERESULT;
    @JsonProperty("CPCFEETRADERESULT")
    private int cPCFEETRADERESULT;
    @JsonProperty("FEEPROVEF04")
    private String fEEPROVEF04;
    @JsonProperty("FITPROVFLAG")
    private int fITPROVFLAG;
    @JsonProperty("GANTRYPASSCOUNTBEFORE")
    private int gANTRYPASSCOUNTBEFORE;
    @JsonProperty("FEEPROVBEGINHEXFIT")
    private String fEEPROVBEGINHEXFIT;
    @JsonProperty("FEEPROVBEGINTIMEFIT")
    private String fEEPROVBEGINTIMEFIT;
    @JsonProperty("TOLLINTERVALSIGN")
    private int tOLLINTERVALSIGN;
    @JsonProperty("PROVMINFEECALCMODE")
    private String pROVMINFEECALCMODE;
    @JsonProperty("FEEPROVBEGINTIME")
    private String fEEPROVBEGINTIME;
    @JsonProperty("FEESUMLOCALAFTEREF04")
    private String fEESUMLOCALAFTEREF04;
    @JsonProperty("LASTGANTRYFEEPASS")
    private String lASTGANTRYFEEPASS;
    @JsonProperty("LASTGANTRYMILEPASS")
    private String lASTGANTRYMILEPASS;
    @JsonProperty("RSUMANUID")
    private String rSUMANUID;
    @JsonProperty("FEEDATAVERSION")
    private String fEEDATAVERSION;
    @JsonProperty("GANTRYHEXOPPOTIME")
    private String gANTRYHEXOPPOTIME;
    @JsonProperty("OBUPAYFEESUMAFTERNOFIT")
    private String oBUPAYFEESUMAFTERNOFIT;
    @JsonProperty("OBUFEESUMAFTERNOFIT")
    private String oBUFEESUMAFTERNOFIT;
    @JsonProperty("OBUMILEAGEAFTERNOFIT")
    private String oBUMILEAGEAFTERNOFIT;
    @JsonProperty("OBUVEHICLEUSERTYPE")
    private String oBUVEHICLEUSERTYPE;
    @JsonProperty("FEEPROVBEGINHEXBEFORE")
    private String fEEPROVBEGINHEXBEFORE;
    @JsonProperty("GANTRYFITRESULT")
    private String gANTRYFITRESULT;
    @JsonProperty("GANTRYFITVERSION")
    private String gANTRYFITVERSION;
    @JsonProperty("GANTRYFITHEXS")
    private String gANTRYFITHEXS;
    @JsonProperty("GANTRYFITTOLLS")
    private String gANTRYFITTOLLS;
    @JsonProperty("GANTRYFITNPROVPAYFEE")
    private String gANTRYFITNPROVPAYFEE;
    @JsonProperty("GANTRYFITNPROVDISCONTFEE")
    private String gANTRYFITNPROVDISCONTFEE;
    @JsonProperty("GANTRYFITNPROVREALFEE")
    private String gANTRYFITNPROVREALFEE;
    @JsonProperty("GANTRYFITNPROVMILES")
    private String gANTRYFITNPROVMILES;
    @JsonProperty("GANTRYFITNPROVPAYFEEGROUP")
    private String gANTRYFITNPROVPAYFEEGROUP;
    @JsonProperty("GANTRYFITNPROVDISCONTFEEGROUP")
    private String gANTRYFITNPROVDISCONTFEEGROUP;
    @JsonProperty("GANTRYFITNPROVREALFEEGROUP")
    private String gANTRYFITNPROVREALFEEGROUP;
    @JsonProperty("GANTRYFITNPROVPAYFEESUM")
    private String gANTRYFITNPROVPAYFEESUM;
    @JsonProperty("GANTRYFITNPROVREALFEESUM")
    private String gANTRYFITNPROVREALFEESUM;
    @JsonProperty("GANTRYFITNPROVMILESUM")
    private String gANTRYFITNPROVMILESUM;
    @JsonProperty("GANTRYFITNPROVPREREALFEE")
    private String gANTRYFITNPROVPREREALFEE;
    @JsonProperty("PATHFITDESC")
    private String pATHFITDESC;
    @JsonProperty("GANTRYFITNEIGHBORPROVCODE")
    private String gANTRYFITNEIGHBORPROVCODE;
    @JsonProperty("FEESPARE3")
    private String fEESPARE3;


    @Override
    public String getID() {
        return this.getTRADEID();
    }

    public boolean isEtc() {
        return this.getMEDIATYPE() != 1;
    }
}
