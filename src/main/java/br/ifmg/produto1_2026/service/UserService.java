package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.UserDTO;
import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.repositories.UserRepository;
import br.ifmg.produto1_2026.resources.exception.databaseException;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
    public UserDTO insert(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);

        return new UserDTO(user);
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

        User entity = userRepository.getReferenceById(id);

        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());

        userRepository.save(entity);
        return new UserDTO(entity);
    }
}
