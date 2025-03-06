package com.stephenowinosewstyle.Sew_Style_Backend.role;


import com.fasterxml.jackson.annotation.JsonValue;
public enum Role {
        USER,
        TAILOR,
        ADMIN;

        // This method will ensure the enum is serialized as a string
        @JsonValue
        public String getRoleString() {
                return this.name();
        }

}
