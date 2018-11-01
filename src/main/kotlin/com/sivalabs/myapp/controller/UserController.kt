package com.sivalabs.myapp.controller

import com.sivalabs.myapp.config.Loggable
import com.sivalabs.myapp.model.UserDTO
import com.sivalabs.myapp.model.UserProfile
import com.sivalabs.myapp.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import org.springframework.http.HttpStatus.CREATED

@RestController
@RequestMapping("/api/users")
@Loggable
class UserController @Autowired
constructor(private val userService: UserService) {

    private val log = LoggerFactory.getLogger(UserController::class.java)

    val users: List<UserDTO>
        @GetMapping("")
        get() {
            log.info("process=get-users")
            return userService.getAllUsers()
        }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long?): ResponseEntity<UserProfile> {
        log.info("process=get-user, user_id={}", id)
        val userProfile = userService.getUserProfile(id)
        return userProfile
                .map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("")
    @ResponseStatus(CREATED)
    fun createUser(@RequestBody user: UserDTO): UserDTO {
        log.info("process=create-user, user_email={}", user.email)
        return userService.createUser(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long?, @RequestBody user: UserDTO): UserDTO {
        log.info("process=update-user, user_id={}", id)
        user.id = id
        return userService.updateUser(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long?) {
        log.info("process=delete-user, user_id={}", id)
        userService.deleteUser(id!!)
    }
}