/*     */ package com.tian.model;
/*     */ 
/*     */ import com.tian.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TagEntryModel
/*     */ {
/*  22 */   private String protoEntry = null;
/*     */ 
/*  24 */   private String endEntry = "";
/*     */ 
/*  28 */   private String tagEntry = null;
/*     */ 
/*  32 */   private String tagPos = null;
/*     */ 
/*  38 */   private boolean protoTag = false;
/*     */ 
/*  44 */   private List<TagSimpleEntry> entryList = null;
/*     */ 
/*     */   public TagEntryModel() {
/*     */   }
/*     */   public TagEntryModel(String tagEntry, String tagPos) {
/*  49 */     this.tagEntry = tagEntry;
/*  50 */     this.tagPos = tagPos;
/*     */   }
/*     */ 
/*     */   public String getTagEntry() {
/*  54 */     return this.tagEntry;
/*     */   }
/*     */ 
/*     */   public void setTagEntry(String tagEntry) {
/*  58 */     this.tagEntry = tagEntry;
/*     */   }
/*     */ 
/*     */   public String getTagPos() {
/*  62 */     return this.tagPos;
/*     */   }
/*     */ 
/*     */   public void setTagPos(String tagPos) {
/*  66 */     this.tagPos = tagPos;
/*     */   }
/*     */ 
/*     */   public void setProtoTag(boolean protoTag) {
/*  70 */     this.protoTag = protoTag;
/*     */   }
/*     */   public boolean isProtoTag() {
/*  73 */     return this.protoTag;
/*     */   }
/*     */ 
/*     */   public void setProtoEntry(String protoEntry) {
/*  77 */     this.protoEntry = protoEntry;
/*  78 */     this.endEntry = protoEntry;
/*     */   }
/*     */ 
/*     */   public void addTagEntry(TagSimpleEntry entryModel)
/*     */   {
/*  87 */     if (this.entryList == null) {
/*  88 */       this.entryList = new ArrayList();
/*     */     }
/*  90 */     this.entryList.add(entryModel);
/*     */   }
/*     */ 
/*     */   public String getResultString()
/*     */     throws Exception
/*     */   {
/* 100 */     if (isProtoTag()) {
/* 101 */       return this.protoEntry;
/*     */     }
/*     */ 
/* 104 */     if ((this.entryList == null) || (this.entryList.size() == 0)) {
/* 105 */       throw new Exception(this.protoEntry + "未找到相应的分词结果！");
/*     */     }
/* 107 */     StringBuilder sb = new StringBuilder();
/*     */ 
/* 109 */     for (TagSimpleEntry model : this.entryList) {
/* 110 */       sb.append("<w pos=\"");
/* 111 */       sb.append(StringUtil.orderXmlString(model.getTagPos()));
/* 112 */       sb.append("\">");
/* 113 */       sb.append(model.getTagEntry());
/* 114 */       sb.append("</w>");
/*     */     }
/*     */ 
/* 117 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public void dealTagResult(TagSimpleEntry tagEntry) throws Exception
/*     */   {
/* 122 */     if (isProtoTag()) {
/* 123 */       return;
/*     */     }
/*     */ 
/* 126 */     String tagStr = tagEntry.getTagEntry();
/* 127 */     String protoStart = this.endEntry.substring(0, tagStr.length());
/*     */ 
/* 129 */     if (!tagStr.equals(protoStart)) {
/* 130 */       throw new Exception("赋码后的结果与原始单词不匹配！ " + this.protoEntry);
/*     */     }
/*     */ 
/* 133 */     this.endEntry = this.endEntry.substring(tagStr.length(), this.endEntry.length());
/*     */ 
/* 135 */     addTagEntry(tagEntry);
/*     */   }
/*     */ 
/*     */   public boolean isEnd()
/*     */   {
/* 144 */     if (this.endEntry.equals("")) {
/* 145 */       return true;
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public String getProtoEntry() {
/* 151 */     return this.protoEntry;
/*     */   }
/*     */   public String toString() {
/* 154 */     return this.protoEntry;
/*     */   }
/*     */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.model.TagEntryModel
 * JD-Core Version:    0.6.2
 */