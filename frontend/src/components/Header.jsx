export default function Header({memberCount, expenseCount, expenseSum}) {
    // Create a NumberFormat object for CAD Dollars in English
    const formatterCAD = new Intl.NumberFormat('en-US', { // TODO move to separate formatting helper file
    style: 'currency',
    currency: 'CAD',
    });

    return (
        <header>
            <div className="header-content">
                <div className="logo">Divido</div>
                <div className="stats">
                    <div className="stat-item">
                        <h3>{memberCount}</h3>
                        <p>Members</p>
                    </div>
                    <div className="stat-item">
                        <h3>{formatterCAD.format(expenseSum)}</h3>
                        <p>Total Expenses</p>
                    </div>
                    <div className="stat-item">
                        <h3>{expenseCount}</h3>
                        <p>Transactions</p>
                    </div>
                </div>
            </div>
        </header>
    )
}