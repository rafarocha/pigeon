package br.eng.mosaic.pigeon.server.service;

import br.eng.mosaic.pigeon.common.domain.User;
import br.eng.mosaic.pigeon.common.dto.UserInfo;
import br.eng.mosaic.pigeon.server.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository;
	
	public User connect(UserInfo userInfo) {
		
		User user = userRepository.getAccount( userInfo.id );
		if ( user == null ) {
			/*
			 * TODO register and confirm registration
			 * TODO refactor code below, argh!
			 */
			user = new User();
			user.setEmail( userInfo.id );
			user.setName( userInfo.name );
			userRepository.insert(user);
		}
		
		updateInfoStatisticals(user);
		userRepository.update( user );
		
		return user;
	}
	
	private void updateInfoStatisticals(User user) {}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}