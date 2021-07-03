package pe.edu.upc.joinsport.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.utils.Segmento;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	private static final long serialVersionUID = 1L;

	// Inyección de dependencia
	private Primaryuser primaryuser;

	public UserDetails(Primaryuser primaryuser) {
		super();
		this.primaryuser = primaryuser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		this.primaryuser.getAuthorities().forEach(authority -> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantedAuthorities.add(grantedAuthority);
		});
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.primaryuser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.primaryuser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.primaryuser.isEnable();
	}
	// Get para el segmento y id del segmento
	public Segmento getSegmento() {
		return this.primaryuser.getSegmento();
	}
	public Integer getIdSegmento() {
		return this.primaryuser.getIdSegmento();
	}

}
