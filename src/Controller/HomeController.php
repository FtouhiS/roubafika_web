<?php
// src/Controller/HomeController.php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
class HomeController extends AbstractController
{

    /**
     * @Route("/")
     */
    public function homepage(): Response
    {
        return $this->render('roubafika/home.html.twig');
    }
    public function index(): Response
    {
        return $this->render('roubafika/email.html.twig');
    }
    public function adminDashboard(): Response
    {
        return $this->render('roubafika/adminHome.html.twig');
    }
    public function LivreurDashboard(): Response
    {
        return $this->render('roubafika/livreurHome.html.twig');
    }
}