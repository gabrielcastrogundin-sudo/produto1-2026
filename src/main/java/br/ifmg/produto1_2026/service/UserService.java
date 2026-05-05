package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UserDTO;
import br.ifmg.produto1_2026.dto.UserInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UserRepository;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserDTO::new);

    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {

        User p = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Produto não encotrado "));

        return new UserDTO(p);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {

        User user = new User();
        copyDtoToUser(dto, user);
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepository.save(user);

        return new UserDTO(user);
    }

    private void copyDtoToUser(UserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        user.setPhone(dto.getPhone());
        user.getPerfils().clear();
        for(PerfilDTO perfilDTO : dto.getPerfils()){
            Perfil perfil = perfilRepository.getReferenceById(perfilDTO.getId());
            user.getPerfils().add(perfil);
        }
    }

    @Transactional
    public void delete(Long id) {

        if(!userRepository.existsById(id)) {
            throw new ResourceNotFound("Registro não encontrado" );
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new databaseException(e.getMessage());
        }
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {

        if(!userRepository.existsById(id)) {
            throw new ResourceNotFound("Registro não encontrado" );
        }

        User user = userRepository.getReferenceById(id);

        copyDtoToUser(dto, user);

        userRepository.save(user);
        return new UserDTO(user);
    }
}
