package com.javaweb.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class StaffResponseDTO {

    private String fullName;
    private Long staffId;
    private String checked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffResponseDTO that = (StaffResponseDTO) o;
        return Objects.equals(staffId, that.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }

}
