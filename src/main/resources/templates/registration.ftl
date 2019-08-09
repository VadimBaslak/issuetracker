<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h2>Hello, new user</h2>
    <@l.login "/registration" "Sign up" true />
    ${message?ifExists}
</@c.page>