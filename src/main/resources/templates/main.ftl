<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">Список пользователей </a></span>
    </div>

    <div><a href="/newIssue">Добавить новую задачу</a></div>

    <h4>Поиск по имени задачи</h4>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>

    <h2>Список задач</h2>
    <#list issues as issue>
        <div>
            <a href="/changeIssue"><span>${issue.issueName}</span></a>
            <strong>${issue.status}</strong>
            <span>${issue.startDate}</span>
        </div>
    <#else>
        No issue
    </#list>
</@c.page>