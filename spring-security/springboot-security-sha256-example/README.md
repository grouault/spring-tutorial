# Springboot auth encode sha256 Password Encoder

Password encode with algoritm SHA-256 encoder and custome salt each users

Example:   
- username: `admin` password: `password` -> login success
- username: `user_lock` password: `password` -> user is locked
- username: `user_expired` password: `password` -> user is expired
- username: `user_blocked` password: `password` -> user is blocked