package pe.edu.upc.joinsport.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upc.joinsport.models.entities.Primaryuser;
import pe.edu.upc.joinsport.models.repositories.PrimaryuserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired
	private PrimaryuserRepository primaryuserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Optional<Primaryuser> optional = this.primaryuserRepository.findByUsername(username);
			if (optional.isPresent()) {
				UserDetails userDetails = new UserDetails( optional.get() );
				return userDetails;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UsernameNotFoundException("El usuario ingresado no existe");	
	}
}
