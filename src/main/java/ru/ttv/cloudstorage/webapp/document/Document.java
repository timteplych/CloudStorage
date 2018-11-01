package ru.ttv.cloudstorage.webapp.document;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Timofey Teplykh
 */
@Getter
@Setter
public class Document implements Serializable, Comparable<Document>  {
    private String name;
    private String size;
    private String type;

    public Document(String name, String size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Document other = (Document) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, type);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(@NotNull Document document) {
        return this.getName().compareTo(document.getName());
    }
}