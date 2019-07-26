<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h2>Добавить нового пользователя</h2>
<@l.login "/registration" />
${message?ifExists}
</@c.page>