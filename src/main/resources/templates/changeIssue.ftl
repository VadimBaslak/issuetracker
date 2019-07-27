<#import "parts/common.ftl" as c>
<@c.page>
    <a href="/main"><h4>Вернуться к списку задач</h4></a>
    <div>
        <h2><strong>Имя задачи: </strong>${issue.issueName}</h2>
        <span><strong>Статус: </strong>${issue.status}</span><br>
        <span><strong>Дата создания:</strong> ${issue.startDate}</span><br>
        <span><strong>Автор: </strong>${issue.author.getUsername()}</span><br>
        <span><strong>Описание: </strong>${issue.description}</span><br>
        <h3><strong>Список комментариев</strong>:</h3>
    </div>


    <div>
        <h3>Добавить комментарий: </h3>
        <form action="/issue" method="post">
            <select name="status">
                <#list statusList as status>
                    <option name="status">${status.getStatus()}</option>
                </#list>
            </select>
            <input type="hidden" name="issueId" value="${issue.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Сохранить</button>
        </form>
        изменяемый статус<br>
        текст<br>
    </div>
</@c.page>