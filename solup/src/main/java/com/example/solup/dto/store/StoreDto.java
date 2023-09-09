package com.example.solup.dto.store;

import com.example.solup.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StoreDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String name;
        private String category;

        public Store toEntity(){
            return Store.builder()
                    .name(this.name)
                    .category(this.category)
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private String name;
        private String category;
    }

}
