import logo from '../assets/Logo.ico';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/UseAuthContext';
import '../assets/Styles/Navbar.css';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate('/product');
    logout();
  };

  return (
    <div className='navbar'>
      <img src={logo} alt="Logo" className='logo' />
      <ul>
        <Link to='/'><li className="nav-item">Home</li></Link>
        <Link to='/product'><li className="nav-item">Products</li></Link>
        <Link to='/about'><li className="nav-item">About</li></Link>

        {user ? (
          <div className="dropdown">
            <li className="nav-item">Profile</li>
            <div className="dropdown-content">
              <button className="dropdown-button" onClick={() => navigate('/profile')}>View Profile</button>
              <button className="dropdown-button" onClick={() => navigate('/cart')}>Cart</button>
              <button className="dropdown-button" onClick={handleLogout}>Sign Out</button>
            </div>
          </div>
        ) : (
          <Link to='/signin'><li className="nav-item">Sign In</li></Link>
        )}
      </ul>
    </div>
  );
};

export default Navbar;
