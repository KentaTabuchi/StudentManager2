package com.kenta.tabuchi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

public class CsvReader {
	private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);
	/**
	 * This method adds record to database Table from CSV file that uploaded by user.
	 * @param uploadFile
	 * @param repository
	 */
	public void importCsv(MultipartFile uploadFile,StudentRepository repository) {
	
		List<String> lines=fileContents(uploadFile);
		lines.forEach(line->{
			Student student = new Student();
			String[] data = line.split(",", -1);
			student.setNo(data[1]);
			student.setName(data[2]);
			student.setRoma(data[3]);
			student.setSex(data[4]);
			student.setPhone(data[5]);
			student.setEmail(data[6]);
			student.setPostno(data[7]);
			student.setAddress1(data[8]);
			student.setAddress2(data[9]);
			student.setAddress3(data[10]);
			student.setAddress4(data[11]);
			student.setBirthday(data[12]);
			student.setGraduation(data[13]);
			repository.saveAndFlush(student);
		});
	}
	
	/**
	 * @param uploadFile
	 * @return
	 * This method created for reading test.
	 */
	public List<String> getLinesFromCsv(MultipartFile uploadFile) {

		List<String> result = new ArrayList<String>();
	
		List<String> lines=fileContents(uploadFile);
		lines.forEach(line->{
			String[] data = line.split(",", -1);
			result.add(data[0]);
			result.add(data[1]);
			result.add(data[2]);
			result.add(data[3]);
			result.add(data[4]);
			result.add(data[5]);
			result.add(data[6]);
			result.add(data[7]);
			result.add(data[8]);
			result.add(data[9]);
			result.add(data[10]);
			result.add(data[11]);
			result.add(data[12]);
			result.add(data[13]);
		});
		return result;
	}
	
	/**
	 * This method open file from posted by user at "index.html".
	 * @param uploadFile 
	 * @return
	 */
	private List<String> fileContents(MultipartFile uploadFile) {
		List<String> lines = new ArrayList<String>();
		String line = null;
		try {
			InputStream stream = uploadFile.getInputStream();			
			Reader reader = new InputStreamReader(stream);
			BufferedReader buf= new BufferedReader(reader);
			while((line = buf.readLine()) != null) {
				lines.add(line);
			}
			line = buf.readLine();
			buf.close();
		} catch (IOException e) {
			line = "Can't read contents.";
			lines.add(line);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
	
	public void exportCSV(HttpServletResponse response,StudentRepository repository) {
        
        //response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=shift-jis");
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"studentdirectory.csv\"");
       
        List<Student> list = repository.findAll();
		  try (PrintWriter pw = response.getWriter()) {

			  StringBuilder sb= new StringBuilder();
			  final String comma=",";
			  final String newline="\r\n";
			  list.forEach(student->{
				   sb.append(student.getId());sb.append(comma);
				   sb.append(student.getNo());sb.append(comma);
				   sb.append(student.getName());sb.append(comma);
				   sb.append(student.getRoma());sb.append(comma);
				   sb.append(student.getSex());sb.append(comma);
				   sb.append(student.getPhone());sb.append(comma);
				   sb.append(student.getEmail());sb.append(comma);
				   sb.append(student.getPostno());sb.append(comma);
				   sb.append(student.getAddress1());sb.append(comma);
				   sb.append(student.getAddress2());sb.append(comma);
				   sb.append(student.getAddress3());sb.append(comma);
				   sb.append(student.getAddress4());sb.append(comma);
				   sb.append(student.getBirthday());sb.append(comma);
				   sb.append(student.getGraduation());sb.append(comma);
				   
				   sb.append(newline);
			  });
			  pw.print(sb.toString());  //write to CSV file;
		  } catch (IOException e) {
		  e.printStackTrace();
		  }
	}
}
