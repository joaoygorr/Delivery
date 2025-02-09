package br.com.delivery.controllers;

import br.com.delivery.modules.user.mapper.UserMapper;
import br.com.delivery.records.user.ResponseRecord;
import br.com.delivery.records.user.UserLoginRecord;
import br.com.delivery.records.user.UserRegisterRecord;
import br.com.delivery.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoint Related Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user",
            description = "Perform a user authentication based on the credentials provided and returns an authentication token.")
    public ResponseEntity<ResponseRecord> login(@RequestBody @Valid UserLoginRecord loginRecord) {
        ResponseRecord user = this.userService.login(userMapper.toEntityLogin(loginRecord));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    @Operation(summary = "Record a new user",
            description = "Create a new user in the system based on the data provided in the request. Returns the details of the registered user."
    )
    public ResponseEntity<ResponseRecord> register(@RequestBody @Valid UserRegisterRecord requestRecord) {
        ResponseRecord user = this.userService.register(userMapper.toEntityRegister(requestRecord));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
