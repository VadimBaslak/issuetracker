<#import "parts/common.ftl" as c>
<#import "parts/pages.ftl" as p>


<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/issue" class="form-inline">
                <div class="navbar-text"><input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by issue name"></div>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-striped table-sm">
        <caption>Issue list</caption>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Status</th>
            <th scope="col">Date of creation</th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as issue>
            <tr>
                <td><a href="/issue/${issue.id}"><span class="text-break">${issue.issueName}</span></a></td>
                <td><span class="text-break">${issue.status}</span></td>
                <td><span class="text-break">${issue.startDate}</span></td>
            </tr>
        <#else>
            <tr>
                <td colspan="3">No issue</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <@p.pager url page />
</@c.page>