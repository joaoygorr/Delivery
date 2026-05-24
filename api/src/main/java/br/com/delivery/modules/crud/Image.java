package br.com.delivery.modules.crud;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Image implements Serializable {

    private String urlImage;

    private byte[] data;

    @Column(name = "imagem_nome")
    private String name;

    private String type;

    private Integer size;

    public boolean isFromUrl() {
        return urlImage != null && !urlImage.isBlank();
    }

    public boolean isBinary() {
        return data != null && data.length > 0;
    }
}
