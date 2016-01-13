package org.swtk.data.wordnet.mda.utils;

import java.io.File;

public interface WordnetMdaConstants {

	String OutFileIndexByIdInstance = "WordnetNounIndexIdInstance#n1#n2#n3#n4.java";

	String OutFileIndexByIdController = "WordnetNounIndexIdController.java";

	String OutFileIndexByNameInstance = "WordnetNounIndexNameInstance#n1#n2#n3.java";

	String OutFileIndexByNameController = "WordnetNounIndexNameController#n1#n2.java";

	String OutFileIndexByNameControllerMeta = "WordnetNounIndexNameController.java";

	String OutFileFrameInstance = "WordnetNounFrame#n1#n2#n3#n4.java";

	String OutFileFrameController = "WordnetNounFrameController.java";

	File SrcFileIndexNoun = new File("../data-wordnet-mda/src/main/resources/wordnet/index.noun");

	File SrcFileIndexNounTestSet = new File("../data-wordnet-mda/src/main/resources/wordnet/index-subset.noun");

	File SrcFileDataNoun = new File("../data-wordnet-mda/src/main/resources/wordnet/data.noun");

	String OutPathBase = "/Users/craigtrim/workspaces/public/swtk/commons/projects/";

	String OutPathIndexByIdInstance = OutPathBase + "commons-dict-wordnet-indexbyid/src/main/java/org/swtk/commons/dict/wordnet/indexbyid/instance/p#n1/p#n2/";

	String OutPathIndexByIdController = OutPathBase + "commons-dict-wordnet-indexbyid/src/main/java/org/swtk/commons/dict/wordnet/indexbyid/controller/";

	String OutPathIndexByNameInstance = OutPathBase + "commons-dict-wordnet-indexbyname/src/main/java/org/swtk/commons/dict/wordnet/indexbyname/instance/#n1/#n2/#n3/";

	String OutPathIndexByNameController = OutPathBase + "commons-dict-wordnet-indexbyname/src/main/java/org/swtk/commons/dict/wordnet/indexbyname/controller/#n1/#n2/";

	String OutPathIndexByNameMetaController = OutPathBase + "commons-dict-wordnet-indexbyname/src/main/java/org/swtk/commons/dict/wordnet/indexbyname/controller/";

	String OutPathNounFrameInstance = OutPathBase + "commons-dict-wordnet-frames/src/main/java/org/swtk/commons/dict/wordnet/frames/instance/p#n1/p#n2/";

	String OutPathNounFrameController = OutPathBase + "commons-dict-wordnet-frames/src/main/java/org/swtk/commons/dict/wordnet/frames/controller/";

	File TemplateIndexByIdInstance = new File("../data-wordnet-mda/Library/template/index/byid/IndexByIdInstanceTemplate.java");

	File TemplateIndexByIdController = new File("../data-wordnet-mda/Library/template/index/byid/IndexByIdInstanceController.java");

	File TemplateIndexByNameInstance = new File("../data-wordnet-mda/Library/template/index/byname/IndexByNameInstanceTemplate.java");

	File TemplateIndexByNameController = new File("../data-wordnet-mda/Library/template/index/byname/IndexByNameControllerTemplate.java");

	File TemplateIndexByNameControllerMeta = new File("../data-wordnet-mda/Library/template/index/byname/IndexByNameControllerMetaTemplate.java");

	File TemplateNounFrameInstance = new File("../data-wordnet-mda/Library/template/frame/byid/NounFrameByIdInstanceTemplate.java");

	File TemplateNounFrameController = new File("../data-wordnet-mda/Library/template/frame/byid/NounFrameByIdControllerTemplate.java");
}
