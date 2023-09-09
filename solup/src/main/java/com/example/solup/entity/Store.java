package com.example.solup.entity;

import com.example.solup.dto.store.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// User가 운영하는 가게 정보
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    public StoreDto.Response toDto(){
        return StoreDto.Response.builder()
                .id(this.id)
                .name(this.name)
                .category(this.category)
                .build();
    }
}
