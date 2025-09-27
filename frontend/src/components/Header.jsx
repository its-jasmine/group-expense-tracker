export default function Header() {
    return (
        <header>
            <div className="header-content">
                <div className="logo">Divido</div>
                <div className="stats">
                    <div className="stat-item">
                        <h3>5</h3>
                        <p>Members</p>
                    </div>
                    <div className="stat-item">
                        <h3>$847.50</h3>
                        <p>Total Expenses</p>
                    </div>
                    <div className="stat-item">
                        <h3>12</h3>
                        <p>Transactions</p>
                    </div>
                </div>
            </div>
        </header>
    )
}