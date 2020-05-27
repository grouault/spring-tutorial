insert into user_detail(
    id,
    username,
    encript_password,
    hash_id,
    enabled,
    blocked,
    expired,
    last_login,
    created_by,
    created_date,
    last_updated_by,
    last_udpated_date)
values
  (uuid(), 'admin',  'sDqnZ7zFOcr7HzC3qijc2/i4g5GTXcsPIJHfngz0954=', 'Xm8yXzph',  true, false, false, null, 'migration', now(), null, null),
  (uuid(), 'user_lock',  'QI8d+E4JpcXU1RonwaoS0dkwrymYPFfKSBm6maDFcBI=', 'jFF87z3s',  false, false, true, null, 'migration', now(), null, null),
  (uuid(), 'user_expired',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  false, false, true , null, 'migration', now(), null, null),
  (uuid(), 'user_blocked',  'eb63232d34ee1afa03a6a682488aef4d517cacd84ddf9a99d3458b460f0e04cb', '123456',  true, true, false,  null, 'migration', now(), null, null);