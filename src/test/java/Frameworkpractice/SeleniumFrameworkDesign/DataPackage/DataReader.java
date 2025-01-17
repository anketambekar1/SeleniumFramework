package Frameworkpractice.SeleniumFrameworkDesign.DataPackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Utf8;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap () throws IOException {
		
		//read json to string 
	String jsonContent =
			FileUtils.readFileToString(new File("C:\\Users\\91997\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\Frameworkpractice\\SeleniumFrameworkDesign\\DataPackage\\PurchaseOrder.json"),
			StandardCharsets.UTF_8);
		
	
	//convert string to hashmap jackson databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent , new TypeReference<List<HashMap<String, String>>>() {
	});
	return data;
	
	}

}
 