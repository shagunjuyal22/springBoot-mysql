package com.mysql.mysqlproject.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.mysqlproject.model.Parameter;
import com.mysql.mysqlproject.model.Team;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class EntityRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void getTeamById(int team_id) {
		String sql = "select * from teams where team_id = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,team_id);
		//Team team = jdbcTemplate.queryForObject(sql, new Object[] {team_id}, new TeamMapper());
		//System.out.println("TEam is "+ team.getTeam_id() + team.getName() + team.getNummer()+ team.getFirmen_id());
		
	}
	
	public void getTeilProjekteById(int teilprojekte_id) throws NoSuchAlgorithmException, IOException {
		String sql = "select * from teilprojekte where teilprojekte_id =?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,teilprojekte_id);
		List<Parameter> param = TeilProjekteMapper(result);
		String newCksum = getChecksum(param);
		System.out.println("checksum of the obj is" + newCksum);
		String checksum = "0EC9D3A68809592E82BE013E976A6AFB";
		if(!newCksum.equals(checksum))
		{
			System.out.println("Obj is updated");
		}
		
		
		
	}
	
	private List<Parameter> TeilProjekteMapper(Map<String, Object> result) {
		List<Parameter> parameterList = new ArrayList<Parameter>();
		
		result.forEach((k,v) -> {
			if(k.equals("firmen_id")) {
				List<Parameter> children = getFirmenById(v.toString());
				Parameter parameter = new Parameter(k,null,children);
				/*parameter.setTitle("firmen");
				parameter.setValue(null);
				parameter.setChildren(children);*/
				parameterList.add(parameter);
				
				
			}
			else {
			Parameter parameter = new Parameter(k,v.toString(),null);
			/*parameter.setTitle(k);
			parameter.setValue(v.toString());
			parameter.setChildren(null);*/
			parameterList.add(parameter);
			}
			
		});
		
		System.out.println("teilProject details are");
		parameterList.forEach((param)->{
			//System.out.println("title is "+ param.getTitle() + "Value is" + param.getValue());
			
		});
		return parameterList;
		
		
	}

	private List<Parameter>  getFirmenById(String value) {
		String sql = "select * from firmen where firmen_id =?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql,value);
		return firmenMapper(result);
	}

	private List<Parameter>  firmenMapper(Map<String, Object> result) {
		List<Parameter> parameterList = new ArrayList<Parameter>();
		result.forEach((k,v) -> {
			Parameter parameter = new Parameter(k,v.toString(),null);
			parameterList.add(parameter);
		});
		return parameterList;
		
	}
	
	public static String getChecksum(List<Parameter> param) throws IOException, NoSuchAlgorithmException {
	    ByteArrayOutputStream baos = null;
	    ObjectOutputStream oos = null;
	    try {
	        baos = new ByteArrayOutputStream();
	        oos = new ObjectOutputStream(baos);
	        oos.writeObject(param);
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] thedigest = md.digest(baos.toByteArray());
	        return DatatypeConverter.printHexBinary(thedigest);
	    } finally {
	        oos.close();
	        baos.close();
	    }
	}

	private static final class TeamMapper implements RowMapper<Team>{

		@Override
		public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
			Team team = new Team();
			team.setTeam_id(rs.getInt(1));
			team.setName(rs.getString(2));
			team.setNummer(rs.getInt(3));
			team.setFirmen_id(rs.getInt(4));
			return team;
			
		}
	}

}
