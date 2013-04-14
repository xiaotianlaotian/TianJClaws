/*     */ import com.tian.model.TagSentenceModel;
/*     */ import com.tian.util.FileUtil;
/*     */ import com.tian.util.TianDebug;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringReader;
/*     */ import java.util.List;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class ClawsBiz
/*     */ {
/*  22 */   private EstClaws estClaws = null;
/*     */ 
/*  24 */   private String inPath = null;
/*  25 */   private String outPath = null;
/*     */ 
/*  27 */   private ParameterParser pp = null;
/*  28 */   private String[] charsFrom = null;
/*  29 */   private String[] charTo = null;
/*     */ 
/*  32 */   private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  33 */   private DocumentBuilder db = null;
/*  34 */   private InputSource is = null;
/*     */ 
/*     */   public ClawsBiz(ParameterParser pp) throws ParserConfigurationException {
/*  37 */     this.estClaws = new EstClaws();
/*  38 */     this.pp = pp;
/*  39 */     this.charsFrom = pp.getReplaceFrom();
/*  40 */     this.charTo = pp.getReplaceTo();
/*     */ 
/*  42 */     this.db = this.factory.newDocumentBuilder();
/*     */   }
/*     */ 
/*     */   public void doClaws(File in, File out)
/*     */     throws Exception
/*     */   {
/*  53 */     if (!in.isDirectory()) {
/*  54 */       throw new Exception("输入文件夹为空！");
/*     */     }
/*     */ 
/*  57 */     this.inPath = in.getAbsolutePath();
/*  58 */     this.outPath = out.getAbsolutePath();
/*     */ 
/*  60 */     dealFile(in);
/*     */   }
/*     */ 
/*     */   private void dealFile(File in)
/*     */     throws Exception
/*     */   {
/*  66 */     if (in.isDirectory()) {
/*  67 */       for (File aFile : in.listFiles()) {
/*  68 */         dealFile(aFile);
/*     */       }
/*     */ 
/*  71 */       return;
/*     */     }
/*     */ 
/*  74 */     String fileName = in.getName();
/*  75 */     String commonPath = in.getAbsolutePath().replace(this.inPath, "").replace(fileName, "");
/*     */ 
/*  77 */     TianDebug.debug("正在处理：" + commonPath + fileName);
/*     */ 
/*  79 */     List<String> sentences = FileUtil.getContentList(in);
/*     */ 
/*  83 */     if (this.pp.isIfOutputXml()) {
/*  84 */       StringBuilder sb = new StringBuilder();
/*  85 */       sb.append("<root>");
/*  86 */       for (String str : sentences) {
/*  87 */         sb.append(str);
/*  88 */         sb.append('\r');
/*     */       }
/*  90 */       sb.append("</root>");
/*     */       try {
/*  92 */         this.is = new InputSource(new StringReader(sb.toString()));
/*  93 */         this.db.parse(this.is);
/*     */       } catch (Exception e) {
/*  95 */         throw new Exception("文件不是xml格式！\n" + e.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 100 */     StringBuilder sb = new StringBuilder();
/*     */ 
/* 103 */     if (this.pp.isIfOutputXml()) {
/* 104 */       sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
/* 105 */       sb.append("\r\n");
/* 106 */       sb.append("<" + this.pp.getOutputXmlRoot() + ">");
/* 107 */       sb.append("\r\n");
/*     */     }
/*     */ 
/* 110 */     String tagText = null;
/*     */ 
/* 112 */     TagSentenceModel sentenceModel = new TagSentenceModel();
/* 113 */     List tagList = null;
/* 114 */     String protoLine = null;
/* 115 */     for (String sen : sentences)
/*     */     {
/* 117 */       if (sen.trim().equals("")) {
/* 118 */         sb.append("\r\n");
/*     */       }
/*     */       else
/*     */       {
/* 123 */         sentenceModel.prepare(replaceChars(sen));
/*     */ 
/* 126 */         protoLine = sentenceModel.getProtoSentence();
/*     */ 
/* 128 */         if (protoLine.trim().equals("")) {
/* 129 */           sb.append(sentenceModel.getDealLine());
/*     */         } else {
/* 131 */           tagText = this.estClaws.getTaggedText(protoLine);
/*     */ 
/* 133 */           tagList = ClawsUtil.getTagEntryList(tagText);
/*     */ 
/* 135 */           sentenceModel.dealTagResult(tagList);
/*     */ 
/* 137 */           sb.append(sentenceModel.getResultString());
/*     */         }
/*     */ 
/* 141 */         sb.append("\r\n");
/*     */ 
/* 143 */         sentenceModel.clear();
/*     */       }
/*     */     }
/* 146 */     if (this.pp.isIfOutputXml()) {
/* 147 */       sb.append("</" + this.pp.getOutputXmlRoot() + ">");
/* 148 */       sb.append("\r\n");
/*     */     }
/*     */ 
/* 151 */     String oStr = this.outPath + commonPath;
/* 152 */     File outPathFile = new File(oStr);
/* 153 */     if (!outPathFile.exists()) {
/* 154 */       FileUtil.createFolder(outPathFile);
/*     */     }
/*     */ 
/* 157 */     File oFile = new File(oStr + this.pp.getFileNameHead() + dealFileName(fileName) + this.pp.getFileNameFoot());
/* 158 */     OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(oFile), "UTF-8");
/*     */     try {
/* 160 */       writer.write(sb.toString());
/*     */     } finally {
/* 162 */       writer.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   private String replaceChars(String str)
/*     */   {
/* 173 */     int i = 0; for (int len = this.charsFrom.length; i < len; i++) {
/* 174 */       str = str.replaceAll(this.charsFrom[i], this.charTo[i]);
/*     */     }
/* 176 */     return str;
/*     */   }
/*     */ 
/*     */   private String dealFileName(String name)
/*     */   {
/* 185 */     int dotIndex = name.lastIndexOf(".");
/* 186 */     return name.substring(0, dotIndex == -1 ? name.length() : dotIndex);
/*     */   }
/*     */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     ClawsBiz
 * JD-Core Version:    0.6.2
 */