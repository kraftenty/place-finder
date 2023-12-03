package com.placefinder.placefinder.entity;


import com.placefinder.placefinder.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * UserDTO를 받아서 User 객체를 생성한다.
     * @param dto
     */
    public User(UserDTO dto) {
        this.name = dto.getName();
        this.password = dto.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    // 비밀번호 수정
    public void updatePassword(String password) {
        this.password = password;
    }

}
