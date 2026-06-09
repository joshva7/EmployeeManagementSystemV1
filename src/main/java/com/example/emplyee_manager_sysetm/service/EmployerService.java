package com.example.emplyee_manager_sysetm.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.emplyee_manager_sysetm.Dto.EmployeDtoName;
import com.example.emplyee_manager_sysetm.Dto.EmployeeDto;
import com.example.emplyee_manager_sysetm.Dto.EmployeeDtoEmail;
import com.example.emplyee_manager_sysetm.Dto.EmployeeFilesDto;
import com.example.emplyee_manager_sysetm.Exceptions.CustomeFilenotfoundException;
import com.example.emplyee_manager_sysetm.Exceptions.CustomeIoexception;
import com.example.emplyee_manager_sysetm.Exceptions.EmployeeNotFound;
import com.example.emplyee_manager_sysetm.emus.Roles;
import com.example.emplyee_manager_sysetm.eneitys.EmplyeeEntity;
import com.example.emplyee_manager_sysetm.repostiry.Employeerepo;
import jakarta.validation.Valid;

@Service
public class EmployerService {
	private final Employeerepo repo;

	public EmployerService(Employeerepo repo) {
		this.repo = repo;
	}

	@Autowired
	public BCryptPasswordEncoder PasswordEncoderhash;

	public EmplyeeEntity CreateUserEmployee(@Valid EmployeeDto emp) {
		EmplyeeEntity empenity = new EmplyeeEntity();
		empenity.setName(emp.getName());
		empenity.setEmail(emp.getEmail());
		empenity.setDesignation(emp.getDesignation());
		empenity.setPhoneNumber(emp.getPhoneNumber());
		empenity.setSalary(emp.getSalary());
		String HashPassword = emp.getPassword();
		empenity.setPassword(PasswordEncoderhash.encode(HashPassword));
		empenity.setRole(Roles.USER);
		return repo.save(empenity);
	}

	public EmplyeeEntity CreateAdminEmploye(@Valid EmployeeDto emp) {
		EmplyeeEntity empenity = new EmplyeeEntity();
		empenity.setName(emp.getName());
		empenity.setEmail(emp.getEmail());
		empenity.setDesignation(emp.getDesignation());
		empenity.setPhoneNumber(emp.getPhoneNumber());
		empenity.setSalary(emp.getSalary());
		String HashPassword = emp.getPassword();
		empenity.setPassword(PasswordEncoderhash.encode(HashPassword));
		empenity.setRole(Roles.ADMIN);
		return repo.save(empenity);
	}

	public EmployeeDto getSingleEmployeById(Long id) {
		EmplyeeEntity emp = repo.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));
		EmployeeDto empDto = new EmployeeDto();
		empDto.setName(emp.getName());
		empDto.setEmail(emp.getEmail());
		empDto.setDesignation(emp.getDesignation());
		empDto.setPhoneNumber(emp.getPhoneNumber());
		empDto.setSalary(emp.getSalary());
		return empDto;
	}

	public List<EmployeDtoName> SearchFindByName(String Name) {
		List<EmplyeeEntity> empname = repo.findByName(Name);
		List<EmployeDtoName> empnameDto = new ArrayList<>();
		for (EmplyeeEntity emp : empname) {
			EmployeDtoName empNameDto = new EmployeDtoName();
			empNameDto.setName(emp.getName());
			empnameDto.add(empNameDto);
		}
		return empnameDto;
	}

	public EmployeeDtoEmail SearchFindByEmail(String Email) {
		EmplyeeEntity empMail = repo.findByEmail(Email);
		EmployeeDtoEmail empdtoMail = new EmployeeDtoEmail();
		empdtoMail.setEmail(empMail.getEmail());
		return empdtoMail;
	}

	public void updateEmployee(Long id, EmployeeDto empdto) {
		EmplyeeEntity emp = repo.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not Found!"));
		emp.setName(empdto.getName());
		emp.setEmail(empdto.getEmail());
		emp.setPhoneNumber(empdto.getPhoneNumber());
		emp.setDesignation(empdto.getDesignation());
		emp.setSalary(empdto.getSalary());
		repo.save(emp);
	}

	public void deleteEmployeById(Long id) {
		repo.findById(id).orElseThrow(() -> new EmployeeNotFound("Employee Not Found!"));
		repo.deleteById(id);
	}

	public List<EmployeeDto> getEmployees() {
		List<EmplyeeEntity> emp = repo.findAll();
		List<EmployeeDto> empDtoList = new ArrayList<>();
		if (emp.isEmpty()) {
			throw new EmployeeNotFound("Employee Not Found!");
		}
		for (EmplyeeEntity empdto : emp) {
			EmployeeDto empDto = new EmployeeDto();
			empDto.setName(empdto.getName());
			empDto.setEmail(empdto.getEmail());
			empDto.setPhoneNumber(empdto.getPhoneNumber());
			empDto.setDesignation(empdto.getDesignation());
			empDto.setSalary(empdto.getSalary());
			empDtoList.add(empDto);
		}
		return empDtoList;
	}

	public List<EmployeeDto> getPagenation(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<EmplyeeEntity> emp = repo.findAll(pageable);
		List<EmployeeDto> empDto = new ArrayList<>();
		emp.stream().forEach(emps -> {
			EmployeeDto empdto = new EmployeeDto();
			empdto.setName(emps.getName());
			empdto.setEmail(emps.getEmail());
			empdto.setPhoneNumber(emps.getPhoneNumber());
			empdto.setDesignation(emps.getDesignation());
			empdto.setSalary(emps.getSalary());
			empDto.add(empdto);
		});
		return empDto;
	}

	public List<EmployeeDto> getPagenationSorting(int page, int size, String sorting) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by(sorting));
		Page<EmplyeeEntity> emp = repo.findAll(pageable);
		List<EmployeeDto> empDto = new ArrayList<>();
		emp.stream().forEach(emps -> {
			EmployeeDto empdto = new EmployeeDto();
			empdto.setName(emps.getName());
			empdto.setEmail(emps.getEmail());
			empdto.setPhoneNumber(emps.getPhoneNumber());
			empdto.setDesignation(emps.getDesignation());
			empdto.setSalary(emps.getSalary());
			empDto.add(empdto);
		});
		return empDto;
	}

	public EmployeeFilesDto uploadFile(MultipartFile file) throws IOException {
		Path path = Paths.get("d:/Uploads");
		Files.createDirectories(path);
		if (file == null) {
			throw new CustomeFilenotfoundException("File not Found");
		}
		String fileType = file.getContentType();
		if (fileType.isEmpty()) {
			throw new CustomeFilenotfoundException("FileType is Null");
		}
		String fileName = file.getOriginalFilename();
		Path fileSave = path.resolve(fileName);
		if (fileType.equalsIgnoreCase("Application/pdf")) {
			file.transferTo(fileSave);
		} else {
			throw new CustomeIoexception("Only Pdf another formant not support");
		}
		EmployeeFilesDto empFiles = new EmployeeFilesDto();
		empFiles.setFileName(file.getOriginalFilename());
		empFiles.setFilePath(fileSave);
		empFiles.setFileType(file.getContentType());
		empFiles.setMessage("Success fully upload");
		return empFiles;
	}

	public List<EmployeeDto> getStoredr(String std) {
		Sort sortList = Sort.by(std).ascending();
		List<EmplyeeEntity> emp = repo.findAll(sortList);
		List<EmployeeDto> empdto = new ArrayList<>();
		for (EmplyeeEntity empsort : emp) {
			EmployeeDto emps = new EmployeeDto();
			emps.setEmail(empsort.getEmail());
			emps.setName(empsort.getName());
			emps.setDesignation(empsort.getDesignation());
			emps.setPhoneNumber(empsort.getPhoneNumber());
			emps.setSalary(empsort.getSalary());
			empdto.add(emps);
		}
		return empdto;
	}

	public EmployeeFilesDto UploadFilesInImage(MultipartFile file) throws RuntimeException, IOException {
		if (file.isEmpty()) {
			throw new CustomeFilenotfoundException("Image File not set");
		}
		Path path = Path.of("d:/Uploads");
		Files.createDirectories(path);
		String fileName = file.getOriginalFilename();
		String fileContent = file.getContentType();
		if (fileContent == null) {
			throw new CustomeFilenotfoundException("FileContent is not set");
		}
		Path fileSave = path.resolve(fileName);
		if (fileContent.equalsIgnoreCase("image/jpeg") || fileContent.equalsIgnoreCase("image/png")) {
			file.transferTo(fileSave);
		} else {
			throw new CustomeIoexception("only image types allow");
		}
		EmployeeFilesDto empfile = new EmployeeFilesDto();
		empfile.setFileName(fileName);
		empfile.setFilePath(fileSave);
		empfile.setFileType(fileContent);
		empfile.setMessage("Sucees save image");
		return empfile;
	}
}