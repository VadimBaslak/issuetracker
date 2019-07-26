<#import "parts/common.ftl" as c>

<@c.page>
<div>
    <h2>Создать новую задачу</h2>
    <form action="/newIssue" method="post">
        <input type="text" name="issueName" placeholder="Введите имя задачи" />
        <input type="text" name="description" placeholder="Введите описание задачи"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Создать</button>
    </form>
</div>
</@c.page>