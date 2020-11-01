package by.bsuir.entity.common;

import by.bsuir.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
//@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image extends AbstractEntity {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "content_length")
    private Long contentLength;

}