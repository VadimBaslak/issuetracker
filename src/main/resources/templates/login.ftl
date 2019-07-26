<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h2>Страница входа в систему</h2>
<@l.login "/login" />
<a href="/registration">Добавить нового пользователя</a>
</@c.page>