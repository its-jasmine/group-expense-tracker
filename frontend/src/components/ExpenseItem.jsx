import formatterCAD from "../formatter"

export default function ExpenseItem({expense}){
    return (
        <tr>
            <td>{ expense.name }</td>
            <td className="expense-amount">{ formatterCAD.format(expense.amount) }</td>
            <td>{ expense.currency }</td>
            <td>{ expense.paidBy.name }</td>
            <td>
                <p className="category">{ expense.category }</p>
            </td>
            <td>
                <button className="btn btn-secondary btn-small"><i className="fa fa-pencil"></i></button>
            </td>
            <td>     
                <button className="action-btn" type="submit"><i className="fa fa-trash-o"></i></button>  
            </td>
        </tr>
    )
}