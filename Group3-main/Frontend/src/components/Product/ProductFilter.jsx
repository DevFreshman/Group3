import { useState } from "react";
import "../../assets/Styles/Product/ProductFilter.css";

const filterOptions = {
  vegetable: [
    { label: "Root Vegetables", value: "ROOT_VEGETABLES" },
    { label: "Leafy Vegetables", value: "LEAFY_VEGETABLES" },
    { label: "Fruitting Vegetables", value: "FRUITING_VEGETABLES" },
  ],
  flower: [
    { label: "Annual Flowers", value: "ANNUAL_FLOWERS" },
    { label: "Perennial Flowers", value: "PERENNIAL_FLOWERS" },
    { label: "Edible Flowers", value: "EDIBLE_FLOWERS" },
  ],
  fruitTree: [
    { label: "Small Fruit Trees", value: "SMALL_FRUIT_TREES" },
    { label: "Large Fruit Trees", value: "LARGE_FRUIT_TREES" },
  ],
  herb: [
    { label: "Culinary Herbs", value: "CULINARY_HERBS" },
    { label: "Medicinal Herbs", value: "MEDICINAL_HERBS" },
  ],
};

const ProductFilter = ({ onFilterChange }) => {
  const [expanded, setExpanded] = useState({
    vegetable: true,
    flower: true,
    fruitTree: true,
    herb: true,
  });

  const [searchText, setSearchText] = useState("");
  const [selectedTypes, setSelectedTypes] = useState([]);

  const toggleCategory = (key) => {
    setExpanded((prev) => ({ ...prev, [key]: !prev[key] }));
  };

  const handleTypeChange = (e) => {
    const { value, checked } = e.target;
    setSelectedTypes((prev) =>
      checked ? [...prev, value] : prev.filter((t) => t !== value)
    );
  };

  const handleSearchChange = (e) => {
    setSearchText(e.target.value);
  };

  const handleSearchClick = () => {
    onFilterChange({
      searchText,
      types: selectedTypes,
    });
  };

  const resetFilters = () => {
    setSearchText("");
    setSelectedTypes([]);
  };

  return (
    <aside className="product-filter">
      <h2 className="filter-title">Filter Products</h2>

      {Object.entries(filterOptions).map(([key, options]) => (
        <div className="filter-group" key={key}>
          <button className="filter-toggle" onClick={() => toggleCategory(key)}>
            {expanded[key] ? "▾" : "▹"} {key.charAt(0).toUpperCase() + key.slice(1)} Types
          </button>
          {expanded[key] && (
            <div className="filter-options">
              {options.map(({ label, value }) => (
                <label key={value}>
                  <input
                    type="checkbox"
                    value={value}
                    checked={selectedTypes.includes(value)}
                    onChange={handleTypeChange}
                  />
                  {label}
                </label>
              ))}
            </div>
          )}
        </div>
      ))}

      <div className="filter-group">
        <h3>Search</h3>
        <input
          type="text"
          placeholder="Search products..."
          value={searchText}
          onChange={handleSearchChange}
          className="search-input"
        />
      </div>

      <div className="filter-group buttons">
        <button onClick={handleSearchClick} className="search-btn">
          Search
        </button>
        <button onClick={resetFilters} className="reset-btn">
          Reset Filters
        </button>
      </div>
    </aside>
  );
};

export default ProductFilter;
