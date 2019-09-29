## Pac4J-CAS Spring Boot starter 单点登录

### 使用说明
1. 将该项目使用jar包，或者maven的方式导入目标项目中
2. 按照配置说明进行相应的配置
3. 在`Spring boot`启动类上加上注解`@EnablePac4jCas`

### 配置说明
#### 最小配置
```properties
# 填CAS服务器的前缀，不需要后反斜杠
cas.server-url-prefix=http://cas.server.com:8088/cas/
# 填CAS服务器的登录地址, 不填则默认为 ${server-url-prefix}+"login"
# cas.server-login-url=http://cas.server.com:8088/cas/login
# 填CAS服务器的登出地址, 不填则默认为 ${server-url-prefix}+"logout"
# cas.server-logout-url=http://cas.server.com:8088/cas/logout
# 填客户端的访问前缀
cas.project-url=http://cas.client.com:8081
# 客户端名称，以供cas服务端识别
cas.client-name=CasClient
# 填cas退出登录过后跳转回来的地址
# cas.logout.default-url=http://cas.client.com:8081/test
# 需要进行登录验证的地址
# cas.include-path=/**
# 不需要登录验证的地址，优先级比include path 高
# cas.exclude-path=/,/test
# 是否全局退出 (注： 如果server端开启‘移除子票据’ 功能的话，需要将cas.logout.local-logout设置为false 
cas.logout.central-logout=true
# 是否退出该应用
cas.logout.local-logout=false
```

#### 全部配置
```properties
# the default client
cas.callback.default-client=
# default url after login if none was requested
cas.callback.default-url=
# whether multiple profiles should be kept
cas.callback.multi-profile=
# the URL path to the callback controller that will receive the redirection request
cas.callback.path=/callback
# whether the session must be renewed after login
cas.callback.renew-session=
# whether profile should be saved in session
cas.callback.save-in-session=
# project clientName, for cas callback
cas.client-name=
# the path patterns that not need login, List
cas.exclude-path=
# the path patterns that need login, List
cas.include-path=
# whether the centralLogout must be performed
cas.logout.central-logout=true
# whether the application logout must be performed
cas.logout.local-logout=false
# default url for logout callback
cas.logout.default-url=
# whether we must destroy the web session during the local logout
cas.logout.destroy-session=
# pattern that logout urls must match
cas.logout.logout-url-pattern=
# the URL path to the logout controller default: /logout
cas.logout.path=/logout
# project-url
cas.project-url=
# cas server login url, default: serverUrlPrefix+"login"
cas.server-login-url=
# cas server logout url, default: serverUrlPrefix+"logout"
cas.server-logout-url=
# cas server url
cas.server-url-prefix=
```