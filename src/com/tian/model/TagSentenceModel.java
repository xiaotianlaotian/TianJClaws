/*     */ package com.tian.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class TagSentenceModel
/*     */ {
/*  19 */   private Pattern htmlTagPattern = Pattern.compile("<(?:/?)(?:\\S*?)[^>]*>");
/*  20 */   private Matcher senMatcher = null;
/*     */ 
/*  22 */   List<TagEntryModel> entryList = null;
/*     */ 
/*  27 */   private String dealLine = null;
/*     */ 
/*     */   public String getDealLine() {
/*  30 */     return this.dealLine;
/*     */   }
/*     */ 
/*     */   public void prepare(String sentence)
/*     */   {
/*  39 */     this.dealLine = sentence;
/*     */ 
/*  41 */     Matcher m = this.htmlTagPattern.matcher(sentence);
/*  42 */     int start = 0;
/*  43 */     int end = 0;
/*  44 */     while (m.find(start))
/*     */     {
/*  46 */       if (start - m.start() == 0) {
/*  47 */         addTagEntry(sentence.substring(start, m.end()));
/*  48 */       } else if (start - m.start() != 0) {
/*  49 */         addNatrueEntry(sentence.substring(start, m.start()));
/*  50 */         addTagEntry(sentence.substring(m.start(), m.end()));
/*     */       }
/*     */ 
/*  53 */       start = m.end();
/*  54 */       end = m.end();
/*     */     }
/*  56 */     int len = sentence.length();
/*  57 */     if (end != len)
/*  58 */       addNatrueEntry(sentence.substring(end, len));
/*     */   }
/*     */ 
/*     */   public void dealTagResult(List<TagSimpleEntry> list)
/*     */     throws Exception
/*     */   {
/*  70 */     int index = 0;
/*  71 */     if (this.entryList == null) {
/*  72 */       throw new Exception("原句子为空！");
/*     */     }
/*  74 */     if ((list == null) || (list.size() == 0)) {
/*  75 */       throw new Exception("赋码后的结果为空！");
/*     */     }
/*  77 */     TagEntryModel model = null;
/*  78 */     for (TagSimpleEntry tagEntry : list) {
/*  79 */       model = (TagEntryModel)this.entryList.get(index);
/*  80 */       while (model.isProtoTag()) {
/*  81 */         index++;
/*  82 */         model = (TagEntryModel)this.entryList.get(index);
/*     */       }
/*     */ 
/*  85 */       model.dealTagResult(tagEntry);
/*  86 */       if (model.isEnd())
/*  87 */         index++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addTagEntry(String str)
/*     */   {
/*  98 */     if (this.entryList == null) {
/*  99 */       this.entryList = new ArrayList();
/*     */     }
/* 101 */     TagEntryModel tagModel = new TagEntryModel();
/* 102 */     tagModel.setProtoEntry(str);
/* 103 */     tagModel.setProtoTag(true);
/*     */ 
/* 105 */     this.entryList.add(tagModel);
/*     */   }
/*     */ 
/*     */   private void addNatrueEntry(String str)
/*     */   {
/* 110 */     if (this.entryList == null) {
/* 111 */       this.entryList = new ArrayList();
/*     */     }
/* 113 */     TagEntryModel model = null;
/* 114 */     str = str.trim();
/* 115 */     String[] result = str.split("\\s");
/* 116 */     String entry = null;
/* 117 */     for (int i = 0; i < result.length; i++) {
/* 118 */       entry = result[i];
/* 119 */       if (entry.length() != 0)
/*     */       {
/* 123 */         model = new TagEntryModel();
/* 124 */         model.setProtoEntry(entry);
/* 125 */         model.setProtoTag(false);
/* 126 */         this.entryList.add(model);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addEntry(TagEntryModel entryModel)
/*     */   {
/* 139 */     if (this.entryList == null) {
/* 140 */       this.entryList = new ArrayList();
/*     */     }
/* 142 */     int size = this.entryList.size();
/* 143 */     if (size == 0)
/* 144 */       this.entryList.add(entryModel);
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 155 */     this.entryList = null;
/*     */   }
/*     */ 
/*     */   public String getProtoSentence()
/*     */   {
/* 166 */     boolean first = true;
/*     */ 
/* 168 */     StringBuilder sb = new StringBuilder();
/* 169 */     for (TagEntryModel entry : this.entryList)
/* 170 */       if (!entry.isProtoTag())
/*     */       {
/* 173 */         if (first)
/* 174 */           first = false;
/*     */         else {
/* 176 */           sb.append(" ");
/*     */         }
/* 178 */         sb.append(entry.getProtoEntry());
/*     */       }
/* 180 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String getResultString()
/*     */     throws Exception
/*     */   {
/* 191 */     StringBuilder sb = new StringBuilder();
/*     */ 
/* 194 */     boolean isNeedSpace = false;
/* 195 */     for (TagEntryModel model : this.entryList) {
/* 196 */       if (model.isProtoTag())
/*     */       {
/* 199 */         if ((isNeedSpace) && (!model.getProtoEntry().subSequence(1, 2).equals("/"))) {
/* 200 */           sb.append(" ");
/* 201 */           isNeedSpace = false;
/*     */         }
/* 203 */         sb.append(model.getResultString());
/*     */       }
/*     */       else
/*     */       {
/* 207 */         if (isNeedSpace) {
/* 208 */           sb.append(" ");
/* 209 */           isNeedSpace = false;
/*     */         }
/* 211 */         sb.append(model.getResultString());
/* 212 */         isNeedSpace = true;
/*     */       }
/*     */     }
/*     */ 
/* 216 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           /home/tian/sihuo/TianJclaws_1.3/
 * Qualified Name:     com.tian.model.TagSentenceModel
 * JD-Core Version:    0.6.2
 */