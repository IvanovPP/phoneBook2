package cz.wistron.phoneBook.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactInformation(

        @Schema(accessMode = Schema.AccessMode.READ_ONLY) //appears only in responses, not in requests
        Integer id,

        //documenting annotations - OpenAPI/Swagger - they do not Validate
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        //validation annotations - some of them Document as well (no need to set "minLength" in Schema)
        @NotNull
        @Size(min = 2, max = 101)
        String firstName,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        String lastName,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Pattern(regexp = "^(\\+|00)[1-9]{1,3}[0-9]{7,32}$")
        String phoneNumber
) {
}
