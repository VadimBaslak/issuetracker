<#import "parts/common.ftl" as c>

<@c.page>
    <h2>User editor</h2>
    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}" class="form-control mb-3">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>