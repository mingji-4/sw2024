import React from "react";
import '../App.css';


const About = () => {
    return (
        <div className="intro-container">
            <h2>JJANG_MinJi</h2>
            <section className="card">
                <h3>About Me</h3>
                <p>
                    coding study of 문과생
                </p>
            </section>
            <section className="card">
                <div className="about-image-container">
                    <img src="/images/cat.jpeg" className="about-image" />
                    <img src="/images/cat2.jpg" className="about-image" />
                </div>
            </section>
            <section className="card">
                <h3>Contact</h3>
                <p>E-mail: <a href="mailto:alswl85160@gmail.com">alswl85160@gmail.com</a></p>
                <p>Please contact <strong>e-mail</strong> and <strong>google chat</strong> 📧</p>
            </section>
        </div>
    );
};

export default About;
