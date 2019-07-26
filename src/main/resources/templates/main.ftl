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
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Status</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <#list issues as issue>
            <tr>
                <td><a href="/issue/${issue.id}"><span>${issue.issueName}</span></a></td>
                <td><strong>${issue.status}</strong></td>
                <td><span>${issue.startDate}</span></td>
            </tr>
        <#else>
            No issue
        </#list>
        </tbody>
    </table>
</@c.page>