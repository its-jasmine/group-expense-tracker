import ExpenseItem from "./ExpenseItem"

export default function Expenses({expenses}) {
    return (
        <div id="expenses-card" className="card">
            <div className="card-header flex-row">
                <h2>Expenses</h2>
                <button className="btn-primary">Add Expense</button>
            </div>
            <table className="expenses-table">
                <thead>
                    <tr>
                        <th>Expense</th>
                        <th>Amount</th>
                        <th>Currency</th>
                        <th>Paid By</th>
                        <th>Category</th>
                        <th colSpan="2" style={{textAlign: 'center'}}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {expenses.map(expense => (
                        <ExpenseItem key={expense.id} expense={expense} />
                    ))}  
                </tbody>
            </table>

        </div>
    )
}