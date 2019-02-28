package kr.or.ddit.util.model;

public class PartUtil {

	public static String getFileNameFromPart(String contentDisposition) {
		String[] splits = contentDisposition.split("; ");
		//splits[0] form-data;
		//splits[1] name="uploadFile"; 
		//splits[2] filename="asd.png";
		String filename=null;
		for(String split : splits){
			if(split.startsWith("filename=")){
				filename = split.substring(split.indexOf("\"")+1,split.lastIndexOf("\""));
			}
		}
		return filename;
	}

}
